package com.rahul.newsapp.headlines.stateholder

import com.rahul.newsapp.base.StateHolder
import com.rahul.newsapp.headlines.domain.LocalArticleUseCase
import com.rahul.newsapp.headlines.domain.TopHeadlinesUseCase
import com.rahul.newsapp.local.entity.LocalArticle
import com.rahul.newsapp.utils.Constants
import com.rahul.newsapp.utils.launchFlow
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
@ViewModelScoped
class TopHeadlinesStateHolder @Inject constructor(
    private val networkStateHolder: NetworkConnectivityStateHolder,
    private val topHeadlinesUseCase: TopHeadlinesUseCase,
    private val localArticleUseCase: LocalArticleUseCase
) : StateHolder<Unit, TopHeadlinesStateHolder.UiState>() {

    override val params = Unit

    override val initialState: UiState = UiState(
        isLoading = true,
    )

    private val _state = MutableStateFlow(initialState)
    override val state: Flow<UiState> = combine(
        networkStateHolder.state,
        fetchTopHeadlinesFromNetwork(),
        fetchLocalHeadlines(),
        handleConnectionState(),
        _state
    ) { _, _, _, _, internalState ->
        UiState(
            isLoading = internalState.isLoading,
            connectedState = internalState.connectedState,
            articleList = internalState.articleList,
        )
    }

    private fun fetchLocalHeadlines() = launchFlow {
        localArticleUseCase(Unit)
            .collect { list ->
                _state.update { it.copy(articleList = list) }
            }
    }


    private fun fetchTopHeadlinesFromNetwork() = launchFlow {
        try {
            topHeadlinesUseCase(Constants.COUNTRY).first().let {
                _state.update {
                    it.copy(
                        isLoading = false,
                        connectedState = networkStateHolder.state.first().connectedState
                    )
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    private fun handleConnectionState() = launchFlow {
        networkStateHolder.state.collect { networkUiState ->
            if (networkUiState.connectedState != _state.value.connectedState) {
                _state.update {
                    it.copy(
                        connectedState = networkUiState.connectedState
                    )
                }
                if (networkUiState.connectedState) {
                    fetchTopHeadlinesOnRetry()
                }
            }
        }
    }

    internal fun fetchTopHeadlinesOnRetry() {
        if (_state.value.articleList?.isEmpty() == true) {
            fetchTopHeadlinesFromNetwork()
        }
    }

    data class UiState(
        val isLoading: Boolean,
        var connectedState: Boolean = true,
        val articleList: List<LocalArticle>? = null,
    )
}

package com.rahul.newsapp.top_headlines.stateholder

import com.rahul.newsapp.base.StateHolder
import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.top_headlines.domain.TopHeadlinesUseCase
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
    private val topHeadlinesUseCase: TopHeadlinesUseCase
) : StateHolder<Unit, TopHeadlinesStateHolder.UiState>() {

    override val params = Unit

    override val initialState: UiState = UiState(
        isLoading = true,
        articleList = emptyList(),
        placeholderList = listOf(
            Article.placeholder,
            Article.placeholder,
            Article.placeholder,
        )
    )

    private val _state = MutableStateFlow(initialState)
    override val state: Flow<UiState> = combine(
        networkStateHolder.state,
        fetchTopHeadlines(),
        handleConnectionState(),
        _state
    ) { _, _, _, internalState ->
        UiState(
            isLoading = internalState.isLoading,
            connectedState = internalState.connectedState,
            articleList = internalState.articleList,
            placeholderList = internalState.placeholderList
        )
    }


    private fun fetchTopHeadlines() = launchFlow {
        topHeadlinesUseCase(Constants.COUNTRY).collect { list ->
            _state.update {
                it.copy(
                    isLoading = false,
                    articleList = list,
                    connectedState = networkStateHolder.state.first().connectedState
                )
            }
        }
    }

    private fun handleConnectionState() = launchFlow {
        networkStateHolder.state.collect { networkUiState ->
            if (networkUiState.connectedState != _state.value.connectedState) {
                if (networkUiState.connectedState) {
                    fetchTopHeadlines()
                }
                _state.update {
                    it.copy(
                        connectedState = networkUiState.connectedState
                    )
                }
            }
        }
    }

    internal fun fetchTopHeadlinesOnRetry() {
        if (_state.value.articleList.isEmpty()) {
            fetchTopHeadlines()
        }
    }


    data class UiState(
        val isLoading: Boolean,
        var connectedState: Boolean = true,
        val articleList: List<Article>,
        val placeholderList: List<Article>,
    )
}
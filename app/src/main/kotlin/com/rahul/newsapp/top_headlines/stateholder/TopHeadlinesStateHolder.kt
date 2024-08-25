package com.rahul.newsapp.top_headlines.stateholder

import com.rahul.newsapp.base.StateHolder
import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.top_headlines.domain.TopHeadlinesUseCase
import com.rahul.newsapp.utils.Constants
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
@ViewModelScoped
class TopHeadlinesStateHolder @Inject constructor(
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
    override val state: Flow<UiState> = _state.onStart {
        fetchTopHeadlines()
    }

    private suspend fun fetchTopHeadlines() {
        topHeadlinesUseCase(Constants.COUNTRY).first().let { list ->
            _state.update {
                it.copy(
                    isLoading = false,
                    articleList = list
                )
            }
        }
    }


    data class UiState(
        val isLoading: Boolean,
        val articleList: List<Article>,
        val placeholderList: List<Article>,
    )
}
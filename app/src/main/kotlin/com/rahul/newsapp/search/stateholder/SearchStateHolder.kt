package com.rahul.newsapp.search.stateholder

import androidx.annotation.DrawableRes
import com.rahul.newsapp.R
import com.rahul.newsapp.base.StateHolder
import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.search.domain.SearchUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
@ViewModelScoped
class SearchStateHolder @Inject constructor(
    private val searchUseCase: SearchUseCase
) : StateHolder<Unit, SearchStateHolder.UiState>() {

    override val params: Unit = Unit
    override val initialState: UiState = UiState(
        isEmpty = true,
        articleList = emptyList(),
        iconResId = R.drawable.ic_search,
        text = ""
    )

    private val _state = MutableStateFlow(initialState)
    override val state: Flow<UiState> = _state

    /**
     * Update the text in the UI when query input changes
     *
     * @param criteria User search string
     */
    private fun onSearchFieldUpdated(criteria: String) {
        with(_state) {
            update {
                it.copy(
                    text = criteria,
                )
            }
        }
    }

    internal suspend fun onTextChange(query: String) {
        onSearchFieldUpdated(criteria = query)

        if (query.length > SEARCH_THRESHOLD) {
            delay(300)
            _state.update { it.copy(isLoading = true) }
            search(query)
        }
    }

    private suspend fun search(query: String) {
        searchUseCase(query).first().let { result ->
            _state.update {
                it.copy(
                    isEmpty = result.isEmpty(),
                    articleList = result,
                    isLoading = false,
                )
            }
        }
    }

    data class UiState(
        val isEmpty: Boolean,
        val isLoading: Boolean = false,
        val articleList: List<Article>,
        @DrawableRes val iconResId: Int,
        val text: String
    )

    companion object {
        private const val SEARCH_THRESHOLD = 2
    }
}
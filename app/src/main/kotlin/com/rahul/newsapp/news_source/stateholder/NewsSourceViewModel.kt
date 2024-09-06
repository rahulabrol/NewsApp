package com.rahul.newsapp.news_source.stateholder

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.newsapp.news.stateholder.NewsByIdStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
@HiltViewModel
class NewsSourceViewModel @Inject constructor(
    topHeadlinesState: NewsSourceStateHolder,
) : ViewModel() {
    internal val state: StateFlow<UiState> = topHeadlinesState.state.map { state ->
        UiState(uiState = state)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState(uiState = topHeadlinesState.initialState)
    )

    /**
     * A class the models the News Source screen UI data
     *
     * @property uiState The News Source state value
     */
    @Immutable
    internal data class UiState(
        val uiState: NewsSourceStateHolder.UiState
    )
}
package com.rahul.newsapp.headlines.stateholder

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Top Headlines Listing viewmodel
 * @property topHeadlinesState
 * @property networkConnectivityStateHolder
 *
 * @constructor Create empty top headlines listing view model
 * Created by abrol at 25/08/24.
 */
@HiltViewModel
class TopHeadlinesViewModel @Inject constructor(
    private val topHeadlinesState: TopHeadlinesStateHolder,
    private val networkConnectivityStateHolder: NetworkConnectivityStateHolder
) : ViewModel() {

    /**
     * A state flow representing the screen ui state.
     */
    internal val state: StateFlow<UiState> = combine(
        topHeadlinesState.state,
        networkConnectivityStateHolder.state
    ) { headlineState, networkState ->
        UiState(topHeadlinesState = headlineState, networkState = networkState)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState(
            topHeadlinesState = topHeadlinesState.initialState,
            networkState = networkConnectivityStateHolder.initialState
        )
    )

    /**
     * This function while click handle 2 things fetch the listings and check the
     * current network state
     *
     */
    internal suspend fun onRetryClick() {
        viewModelScope.launch {
            topHeadlinesState.fetchTopHeadlinesOnRetry()
        }
        networkConnectivityStateHolder.onRetryClick()
    }

    /**
     * A class the models the Top Headlines screen UI data
     *
     * @property topHeadlinesState The top headline state value
     * @property networkState Network state value
     */
    @Immutable
    internal data class UiState(
        val topHeadlinesState: TopHeadlinesStateHolder.UiState,
        val networkState: NetworkConnectivityStateHolder.UiState
    )
}

package com.rahul.newsapp.search.stateholder

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchStateHolder: SearchStateHolder
) : ViewModel() {

    internal val state: StateFlow<UiState> = searchStateHolder.state.map { state ->
        UiState(uiState = state)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState(uiState = searchStateHolder.initialState)
    )

    internal fun onTextChangeEvent(query: String) {
        viewModelScope.launch {
            searchStateHolder.onTextChange(query)
        }
    }

    /**
     * A class the models the News Source screen UI data
     *
     * @property uiState The News Source state value
     */
    @Immutable
    internal data class UiState(
        val uiState: SearchStateHolder.UiState
    )
}

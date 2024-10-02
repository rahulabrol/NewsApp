package com.rahul.newsapp.language.stateholder

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
@HiltViewModel
class LanguagesViewModel @Inject constructor(
    languagesStateHolder: LanguagesStateHolder
) : ViewModel() {
    internal val state: StateFlow<UiState> = languagesStateHolder.state.map { state ->
        UiState(uiState = state)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState(uiState = languagesStateHolder.initialState)
    )

    /**
     * A class the models the News Source screen UI data
     *
     * @property uiState The News Source state value
     */
    @Immutable
    internal data class UiState(
        val uiState: LanguagesStateHolder.UiState
    )
}

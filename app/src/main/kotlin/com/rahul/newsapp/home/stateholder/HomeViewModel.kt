package com.rahul.newsapp.home.stateholder

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahul.newsapp.home.model.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeStateHolder: HomeStateHolder
) : ViewModel() {
    internal val state: StateFlow<UiState> = homeStateHolder.state.map { state ->
        UiState(tabs = state)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UiState(tabs = homeStateHolder.initialState)
    )

    /**
     * An event that is triggered when the user selects a bottom bar tab
     *
     * @param type The tab that was selected.
     */
    internal fun tabSelectedEvent(type: Type) {
        viewModelScope.launch {
            homeStateHolder.tabSelectedEvent(type)
        }
    }

    /**
     * A class the models the Top Headlines screen UI data
     *
     * @property tabs The top headline state value
     */
    @Immutable
    internal data class UiState(
        val tabs: HomeStateHolder.UiState
    )
}

package com.rahul.newsapp.language.stateholder

import com.rahul.newsapp.base.StateHolder
import com.rahul.newsapp.local.entity.Language
import com.rahul.newsapp.utils.Constants
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
@ViewModelScoped
class LanguagesStateHolder @Inject constructor() :
    StateHolder<Unit, LanguagesStateHolder.UiState>() {

    override val params: Unit = Unit

    override val initialState: UiState = UiState(
        sourceList = Constants.LANGUAGES
    )

    private val _state = MutableStateFlow(initialState)
    override val state: Flow<UiState> = _state

    data class UiState(
        val sourceList: List<Language>,
    )
}
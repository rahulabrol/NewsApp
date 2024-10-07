package com.rahul.newsapp.countries.stateholder

import com.rahul.newsapp.base.StateHolder
import com.rahul.newsapp.local.entity.LocalCountry
import com.rahul.newsapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
class CountriesStateHolder @Inject constructor() :
    StateHolder<Unit, CountriesStateHolder.UiState>() {

    override val params: Unit = Unit

    override val initialState: UiState = UiState(
        sourceList = Constants.COUNTRIES
    )

    private val _state = MutableStateFlow(initialState)
    override val state: Flow<UiState> = _state

    data class UiState(
        val sourceList: List<LocalCountry>
    )
}

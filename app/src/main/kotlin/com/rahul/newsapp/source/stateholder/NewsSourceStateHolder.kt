package com.rahul.newsapp.source.stateholder

import com.rahul.newsapp.base.StateHolder
import com.rahul.newsapp.source.domain.NewsSourceUseCase
import com.rahul.newsapp.source.domain.model.Source
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
class NewsSourceStateHolder @Inject constructor(
    private val newsSourceUseCase: NewsSourceUseCase
) : StateHolder<Unit, NewsSourceStateHolder.UiState>() {

    override val params: Unit = Unit

    override val initialState: UiState = UiState(
        isLoading = true,
        sourceList = emptyList(),
        placeholderList = listOf(
            Source.placeholder,
            Source.placeholder,
            Source.placeholder
        )
    )

    private val _state = MutableStateFlow(initialState)
    override val state: Flow<UiState> = _state.onStart {
        fetchNewsSource()
    }

    private suspend fun fetchNewsSource() {
        newsSourceUseCase(Unit).first().let { list ->
            _state.update {
                it.copy(
                    isLoading = false,
                    sourceList = list
                )
            }
        }
    }

    data class UiState(
        val isLoading: Boolean,
        val sourceList: List<Source>,
        val placeholderList: List<Source>
    )
}

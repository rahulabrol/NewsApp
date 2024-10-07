package com.rahul.newsapp.news.stateholder

import androidx.lifecycle.SavedStateHandle
import com.rahul.newsapp.base.StateHolder
import com.rahul.newsapp.local.entity.LocalArticle
import com.rahul.newsapp.news.domain.NewsByIdUseCase
import com.rahul.newsapp.utils.COUNTRIES
import com.rahul.newsapp.utils.LANGUAGES
import com.rahul.newsapp.utils.NEWS_SOURCE
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
class NewsByIdStateHolder @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val newsByIdUseCase: NewsByIdUseCase,
    private val newsByCountryUseCase: NewsByIdUseCase,
    private val newsByLanguageUseCase: NewsByIdUseCase
) : StateHolder<Params, NewsByIdStateHolder.UiState>() {

    override val params = Params(
        id = savedStateHandle.get<String>(key = "id").orEmpty(),
        type = savedStateHandle.get<String>(key = "type").orEmpty()
    )

    override val initialState: UiState = UiState(
        isLoading = true,
        articleList = emptyList(),
        placeholderList = listOf(
            LocalArticle.placeholder,
            LocalArticle.placeholder,
            LocalArticle.placeholder
        )
    )

    private val _state = MutableStateFlow(initialState)
    override val state: Flow<UiState> = _state.onStart {
        when (params.type) {
            NEWS_SOURCE -> fetchNewsBySource()
            COUNTRIES -> fetchNewsByCountry()
            LANGUAGES -> fetchNewsByLanguage()
        }
    }

    private suspend fun fetchNewsByCountry() {
        newsByCountryUseCase(params.id).first().let { list ->
            _state.update {
                it.copy(
                    isLoading = false,
                    articleList = list
                )
            }
        }
    }

    private suspend fun fetchNewsByLanguage() {
        newsByLanguageUseCase(params.id).first().let { list ->
            _state.update {
                it.copy(
                    isLoading = false,
                    articleList = list
                )
            }
        }
    }

    private suspend fun fetchNewsBySource() {
        newsByIdUseCase(params.id).first().let { list ->
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
        val placeholderList: List<LocalArticle>,
        val articleList: List<LocalArticle>
    )
}

data class Params(
    val type: String,
    val id: String
)

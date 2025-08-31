package com.rahul.newsapp.news.stateholder

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.rahul.newsapp.base.StateHolder
import com.rahul.newsapp.local.entity.LocalArticle
import com.rahul.newsapp.navigation.routes.NewsListingByIdRoute
import com.rahul.newsapp.navigation.routes.TestRoute
import com.rahul.newsapp.navigation.routes.TestRouteListNavType
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
import kotlin.reflect.typeOf

/**
 * Converts the SavedStateHandle to a Params object using the first item from the route's test data.
 *
 * Extracts the first item from the `NewsListingById` route's test data and creates a `Params` object
 * with its `id` and `type`.
 *
 * @return A [Params] object containing the ID and type of the first test item.
 * @throws IllegalArgumentException if data extraction fails or test list is empty.
 *
 * @see NewsListingByIdRoute
 * @see Params
 */
private fun SavedStateHandle.toNewsByIdParams(): Params {
    val route = this.toRoute<NewsListingByIdRoute>(
        typeMap = mapOf(typeOf<List<TestRoute>>() to TestRouteListNavType),
    )
    val testItems = route.test
    require(testItems.isNotEmpty()) { "NewsListingByIdRoute.test must contain at least one item" }
    val first = testItems.first()
    require(first.id.isNotBlank()) { "TestRoute.id must not be blank" }
    require(first.type.isNotBlank()) { "TestRoute.type must not be blank" }
    return Params(
        id = first.id,
        type = first.type,
    )
}

/**
 *  A [StateHolder] class responsible for managing the UI state for displaying news articles based on a given ID.
 *  The ID can represent a news source, a country, or a language.
 *  This class uses a [ViewModelScoped] scope, meaning its lifecycle is tied to a ViewModel.
 *
 *  @property params The parameters used to fetch the news articles, obtained from the SavedStateHandle.
 *  @property initialState The initial UI state with loading set to true and placeholder articles.
 *  @property state A [Flow] of [UiState] representing the current state of the UI.
 *
 *  @param savedStateHandle The handle to access saved state.
 *  @param newsByIdUseCase Use case for fetching news by source ID.
 *  @param newsByCountryUseCase Use case for fetching news by country ID.
 *  @param newsByLanguageUseCase Use case for fetching news by language ID.
 *
 *  @see StateHolder
 *  @see NewsByIdUseCase
 */
@ViewModelScoped
class NewsByIdStateHolder @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val newsByIdUseCase: NewsByIdUseCase,
    private val newsByCountryUseCase: NewsByIdUseCase,
    private val newsByLanguageUseCase: NewsByIdUseCase,
) : StateHolder<Params, NewsByIdStateHolder.UiState>() {

    override val params = savedStateHandle.toNewsByIdParams()

    override val initialState: UiState = UiState(
        isLoading = true,
        articleList = emptyList(),
        placeholderList = listOf(
            LocalArticle.placeholder,
            LocalArticle.placeholder,
            LocalArticle.placeholder,
        ),
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
                    articleList = list,
                )
            }
        }
    }

    /**
     * Fetches news articles based on the language specified in the `params.id`.
     *
     * This function uses the `newsByLanguageUseCase` to retrieve a list of articles.
     * It then updates the state to reflect the fetched data, setting `isLoading` to false
     * and updating the `articleList` with the retrieved articles.
     *
     * The function uses `first()` to retrieve the first emission of the Flow returned by `newsByLanguageUseCase`.
     *
     * @throws Exception if an error occurs during data fetching from the use case
     */
    private suspend fun fetchNewsByLanguage() {
        newsByLanguageUseCase(params.id).first().let { list ->
            _state.update {
                it.copy(
                    isLoading = false,
                    articleList = list,
                )
            }
        }
    }

    /**
     * Fetches news articles from a source and updates the view model state.
     */
    private suspend fun fetchNewsBySource() {
        newsByIdUseCase(params.id).first().let { list ->
            _state.update {
                it.copy(
                    isLoading = false,
                    articleList = list,
                )
            }
        }
    }

    /**
     * Represents the UI state for a screen displaying a list of articles.
     *
     * This data class encapsulates the different states the UI can be in, including
     * whether data is loading, a placeholder list to show while loading, and the actual
     * list of articles.
     *
     * @property isLoading A boolean indicating whether data is currently being loaded.
     *                    `true` if loading, `false` otherwise.
     * @property placeholderList A list of [LocalArticle] objects to display as placeholders
     *                          while the actual data is loading.
     * @property articleList A list of [LocalArticle] objects representing the actual articles
     *                      to be displayed when loading is complete.
     */
    data class UiState(
        val isLoading: Boolean,
        val placeholderList: List<LocalArticle>,
        val articleList: List<LocalArticle>,
    )
}

/**
 * Represents a set of parameters used in a specific context.
 *
 * @property type The type of the parameter, used to categorize or identify its purpose.
 * @property id A unique identifier for the parameter.
 */
data class Params(
    val type: String,
    val id: String,
)

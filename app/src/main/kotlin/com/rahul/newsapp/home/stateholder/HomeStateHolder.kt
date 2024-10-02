package com.rahul.newsapp.home.stateholder

import com.rahul.newsapp.R
import com.rahul.newsapp.base.StateHolder
import com.rahul.newsapp.home.model.HomeTab
import com.rahul.newsapp.home.model.Type
import com.rahul.newsapp.home.utils.HomeTestTags
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
@ViewModelScoped
class HomeStateHolder @Inject constructor() : StateHolder<Unit, HomeStateHolder.UiState>() {

    override val params = Unit

    private val _state = MutableStateFlow(
        UiState(
            tabs = tabs(Type.TOP_HEADLINES),
            selectedTab = Type.TOP_HEADLINES
        )
    )

    override val initialState: UiState = UiState(
        tabs = tabs(Type.TOP_HEADLINES),
        selectedTab = Type.TOP_HEADLINES
    )

    /**
     * The flow the the state holder emits it's ui state through
     */
    override val state: Flow<UiState> = _state

    /**
     * A function that's invoked when the user selects a product tab or navigates via ViewPager
     *
     * @param type The product that is represented by the selected tab
     */
    internal suspend fun tabSelectedEvent(type: Type) {
        _state.emit(
            _state.value.copy(
                selectedTab = type,
                tabs = tabs(type)
            )
        )
    }

    private fun tabs(selectedType: Type): List<HomeTab> = buildList {
        Type.entries.forEach {
            add(
                it.ordinal,
                homeTabItem(
                    type = it,
                    isSelected = selectedType == it
                )
            )
        }
    }

    private fun homeTabItem(type: Type, isSelected: Boolean) = when (type) {
        Type.TOP_HEADLINES -> topHeadlines(selected = isSelected)
        Type.NEWS_SOURCE -> newsSource(selected = isSelected)
        Type.COUNTRY -> country(selected = isSelected)
        Type.LANGUAGE -> language(selected = isSelected)
        Type.SEARCH -> search(selected = isSelected)
    }

    private fun topHeadlines(selected: Boolean) = HomeTab(
        type = Type.TOP_HEADLINES,
        labelResId = R.string.top_headlines_title,
        contentDescriptionResId = null,
        iconResId = if (selected) {
            R.drawable.ic_headline_selected
        } else {
            R.drawable.ic_headline
        },
        testTag = HomeTestTags.TAB_TOP_HEADLINES
    )

    private fun newsSource(selected: Boolean) = HomeTab(
        type = Type.NEWS_SOURCE,
        labelResId = R.string.news_source_title,
        contentDescriptionResId = null,
        iconResId = if (selected) {
            R.drawable.ic_news_source_selected
        } else {
            R.drawable.ic_news_source
        },
        testTag = HomeTestTags.TAB_NEWS_SOURCE
    )

    private fun country(selected: Boolean) = HomeTab(
        type = Type.COUNTRY,
        labelResId = R.string.country_title,
        contentDescriptionResId = null,
        iconResId = if (selected) {
            R.drawable.ic_country_selected
        } else {
            R.drawable.ic_country
        },
        testTag = HomeTestTags.TAB_COUNTRY
    )

    private fun language(selected: Boolean) = HomeTab(
        type = Type.LANGUAGE,
        labelResId = R.string.language_title,
        contentDescriptionResId = null,
        iconResId = if (selected) {
            R.drawable.ic_language_selected
        } else {
            R.drawable.ic_language
        },
        testTag = HomeTestTags.TAB_LANGUAGE
    )

    private fun search(selected: Boolean) = HomeTab(
        type = Type.SEARCH,
        labelResId = R.string.search_title,
        contentDescriptionResId = null,
        iconResId = if (selected) {
            R.drawable.ic_search_selected
        } else {
            R.drawable.ic_search
        },
        testTag = HomeTestTags.TAB_SEARCH
    )

    /**
     * A data class the book bottom bar tabs ui state.
     *
     * @property selectedTab - The currently selected tab.
     * @property tabs - The list of bottom bar tabs to display
     */
    data class UiState(
        val selectedTab: Type,
        val tabs: List<HomeTab>
    )
}

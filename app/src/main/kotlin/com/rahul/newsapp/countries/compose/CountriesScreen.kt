package com.rahul.newsapp.countries.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rahul.newsapp.common.compose.NewsItem
import com.rahul.newsapp.countries.stateholder.CountriesViewModel
import com.rahul.newsapp.countries.utils.CountriesTestTags

/**
 * Created by abrol at 25/08/24.
 */
@Composable
internal fun CountriesScreen(
    modifier: Modifier = Modifier,
    viewModel: CountriesViewModel = hiltViewModel(),
    onCountriesItemClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CountriesContent(
        modifier = modifier,
        state = state,
        onCountriesItemClick = onCountriesItemClick
    )
}

@Composable
private fun CountriesContent(
    modifier: Modifier = Modifier,
    state: CountriesViewModel.UiState,
    listState: LazyListState = rememberLazyListState(),
    onCountriesItemClick: (String) -> Unit
) {
    Scaffold(
        modifier = modifier.testTag(CountriesTestTags.SCREEN_ROOT)
    ) { paddingValues ->
        println(paddingValues)
        LazyColumn(
            modifier = Modifier
                .testTag(CountriesTestTags.LISTINGS_COUNTRIES)
                .background(Color.LightGray),
            state = listState
        ) {
            items(items = state.uiState.sourceList) {
                NewsItem(
                    id = { it.id },
                    name = { it.name },
                    onNewsSourceItemClick = onCountriesItemClick
                )
            }
        }
    }
}

package com.rahul.newsapp.news_source.compose

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
import com.rahul.newsapp.news_source.stateholder.NewsSourceViewModel
import com.rahul.newsapp.news_source.utils.NewsSourceTestTags

/**
 * The composable entry point to display the news source tab/screen
 *
 * @param modifier An ordered, immutable collection of modifier elements that decorate or add behavior to
 *                 Compose UI elements.
 *                 For example, backgrounds, padding and click event listeners decorate or add behavior to
 *                 rows, text or buttons.
 *
 * Created by abrol at 25/08/24.
 */
@Composable
internal fun NewsSourceScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsSourceViewModel = hiltViewModel(),
    onNewsSourceItemClick: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    NewsSourceContent(
        modifier = modifier,
        state = state,
        onNewsSourceItemClick = onNewsSourceItemClick,
    )
}

@Composable
private fun NewsSourceContent(
    modifier: Modifier = Modifier,
    state: NewsSourceViewModel.UiState,
    listState: LazyListState = rememberLazyListState(),
    onNewsSourceItemClick: (String) -> Unit,
) {
    Scaffold(
        modifier = modifier.testTag(NewsSourceTestTags.SCREEN_ROOT),
    ) { paddingValues ->
        println(paddingValues)
        LazyColumn(
            modifier = Modifier
                .testTag(NewsSourceTestTags.LISTINGS_NEWS_SOURCE)
                .background(Color.LightGray),
            state = listState
        ) {
            items(items = state.uiState.sourceList) {
                NewsItem(
                    id = { it.id.orEmpty() },
                    name = { it.name },
                    onNewsSourceItemClick = onNewsSourceItemClick
                )
            }
        }
    }
}
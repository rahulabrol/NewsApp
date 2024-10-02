package com.rahul.newsapp.language.compose

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
import com.rahul.newsapp.language.stateholder.LanguagesViewModel
import com.rahul.newsapp.language.utils.LanguagesTestTags

/**
 * Created by abrol at 25/08/24.
 */
@Composable
internal fun LanguagesScreen(
    modifier: Modifier = Modifier,
    viewModel: LanguagesViewModel = hiltViewModel(),
    onLanguagesItemClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LanguagesContent(
        modifier = modifier,
        state = state,
        onLanguagesItemClick = onLanguagesItemClick
    )
}

@Composable
private fun LanguagesContent(
    modifier: Modifier = Modifier,
    state: LanguagesViewModel.UiState,
    listState: LazyListState = rememberLazyListState(),
    onLanguagesItemClick: (String) -> Unit
) {
    Scaffold(
        modifier = modifier.testTag(LanguagesTestTags.SCREEN_ROOT)
    ) { paddingValues ->
        println(paddingValues)
        LazyColumn(
            modifier = Modifier
                .testTag(LanguagesTestTags.LISTINGS_LANGUAGES)
                .background(Color.LightGray),
            state = listState
        ) {
            items(items = state.uiState.sourceList) {
                NewsItem(
                    id = { it.id },
                    name = { it.name },
                    onNewsSourceItemClick = onLanguagesItemClick
                )
            }
        }
    }
}

package com.rahul.newsapp.top_headlines.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rahul.newsapp.common.compose.ArticleItem
import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.local.entity.Source
import com.rahul.newsapp.theme.NewsAppTheme
import com.rahul.newsapp.top_headlines.stateholder.TopHeadlinesStateHolder
import com.rahul.newsapp.top_headlines.stateholder.TopHeadlinesViewModel
import com.rahul.newsapp.top_headlines.utils.TopHeadlinesTestTags
import com.rahul.newsapp.utils.TOP_HEADLINES

/**
 * The composable entry point to display the book car tab/screen
 *
 * @param modifier An ordered, immutable collection of modifier elements that decorate or add behavior to
 *                 Compose UI elements.
 *                 For example, backgrounds, padding and click event listeners decorate or add behavior to
 *                 rows, text or buttons.
 *
 * Created by abrol at 25/08/24.
 */
@Composable
fun PaginationTopHeadlinesScreen(
    modifier: Modifier = Modifier,
    viewModel: TopHeadlinesViewModel = hiltViewModel(),
    screenName: () -> String,
    onBackPress: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TopHeadlinesContent(
        modifier = modifier,
        uiState = { state.topHeadlinesState },
        screenName = screenName,
        onBackPress = onBackPress
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopHeadlinesContent(
    modifier: Modifier = Modifier,
    uiState: () -> TopHeadlinesStateHolder.UiState,
    screenName: () -> String,
    onBackPress: () -> Unit,
    listState: LazyListState = rememberLazyListState()
) {
    Scaffold(
        modifier = modifier.testTag(TopHeadlinesTestTags.SCREEN_ROOT),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = screenName())
                },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = screenName()
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .testTag(TopHeadlinesTestTags.LISTINGS_TOP_HEADLINES)
                .padding(paddingValues = paddingValues)
                .background(Color.LightGray),
            state = listState
        ) {
            items(items = uiState().articleList) {
                ArticleItem { it }
            }
        }
    }
}

@Preview
@Composable
private fun PaginationTopHeadlinesPreview() {
    NewsAppTheme {
        TopHeadlinesContent(uiState = {
            TopHeadlinesStateHolder.UiState(
                isLoading = false,
                articleList = listOf(
                    Article(
                        title = "Test",
                        description = "Textjk aaslfkjahdk faj",
                        imageUrl = "ertryt.png",
                        url = "dfsdg.png",
                        source = Source(sourceId = "2", name = "Source Test")
                    )
                ),
                placeholderList = emptyList(),
            )

        }, screenName = { TOP_HEADLINES },
            onBackPress = {}
        )
    }
}

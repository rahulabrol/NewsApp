package com.rahul.newsapp.top_headlines.compose

import android.net.Uri
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

/**
 * The composable entry point to display the paginated top headlines tab/screen
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
    onArticleItemClick: (Uri) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TopHeadlinesContent(
        modifier = modifier,
        state = state,
        onArticleItemClick = onArticleItemClick,
    )
}

@Composable
private fun TopHeadlinesContent(
    modifier: Modifier = Modifier,
    state: TopHeadlinesViewModel.UiState,
    listState: LazyListState = rememberLazyListState(),
    onArticleItemClick: (Uri) -> Unit
) {
    Scaffold(
        modifier = modifier.testTag(TopHeadlinesTestTags.SCREEN_ROOT),
    ) { paddingValues ->
        println(paddingValues)
        LazyColumn(
            modifier = Modifier
                .testTag(TopHeadlinesTestTags.LISTINGS_TOP_HEADLINES)
                .background(Color.LightGray),
            state = listState
        ) {
            items(items = state.topHeadlinesState.articleList) {
                ArticleItem(article = { it }, onArticleItemClick = onArticleItemClick)
            }
        }
    }
}

@Preview
@Composable
private fun PaginationTopHeadlinesPreview() {
    NewsAppTheme {
        TopHeadlinesContent(
            state = TopHeadlinesViewModel.UiState(
                topHeadlinesState = TopHeadlinesStateHolder.UiState(
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
            ),
            onArticleItemClick = {},
        )
    }
}

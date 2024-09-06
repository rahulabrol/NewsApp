package com.rahul.newsapp.news.compose

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rahul.newsapp.common.EmptyView
import com.rahul.newsapp.common.IndeterminateCircularIndicator
import com.rahul.newsapp.common.compose.ArticleItem
import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.local.entity.Source
import com.rahul.newsapp.news.stateholder.NewsByIdStateHolder
import com.rahul.newsapp.news.stateholder.NewsByIdViewModel
import com.rahul.newsapp.theme.NewsAppTheme
import com.rahul.newsapp.top_headlines.utils.TopHeadlinesTestTags

/**
 * Created by abrol at 06/09/24.
 */

/**
 * The composable entry point to display the news by its Id screen.
 *
 * @param modifier An ordered, immutable collection of modifier elements that decorate or add behavior to
 *                 Compose UI elements.
 *                 For example, backgrounds, padding and click event listeners decorate or add behavior to
 *                 rows, text or buttons.
 *
 * Created by abrol at 25/08/24.
 */
@Composable
internal fun NewsByIdScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsByIdViewModel = hiltViewModel(),
    onArticleItemClick: (Uri) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    NewsByIdContent(
        modifier = modifier,
        state = state,
        onArticleItemClick = onArticleItemClick,
    )
}

@Composable
private fun NewsByIdContent(
    modifier: Modifier = Modifier,
    state: NewsByIdViewModel.UiState,
    listState: LazyListState = rememberLazyListState(),
    onArticleItemClick: (Uri) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag(TopHeadlinesTestTags.SCREEN_ROOT),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state.uiState.isLoading) {
            IndeterminateCircularIndicator()
        } else if (state.uiState.articleList.isEmpty()) {
            EmptyView()
        } else {

            LazyColumn(
                modifier = Modifier
                    .testTag(TopHeadlinesTestTags.LISTINGS_TOP_HEADLINES)
                    .background(Color.LightGray),
                state = listState
            ) {
                items(items = state.uiState.articleList) {
                    ArticleItem(article = { it }, onArticleItemClick = onArticleItemClick)
                }
            }
        }
    }
}

@Preview
@Composable
private fun NewsByIdPreview() {
    NewsAppTheme {
        NewsByIdContent(
            state = NewsByIdViewModel.UiState(
                uiState = NewsByIdStateHolder.UiState(
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
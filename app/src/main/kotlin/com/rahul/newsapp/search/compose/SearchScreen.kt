package com.rahul.newsapp.search.compose

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rahul.newsapp.R
import com.rahul.newsapp.common.compose.ArticleItem
import com.rahul.newsapp.news_source.utils.NewsSourceTestTags
import com.rahul.newsapp.search.stateholder.SearchStateHolder
import com.rahul.newsapp.search.stateholder.SearchViewModel
import com.rahul.newsapp.theme.NewsAppTheme

/**
 * Created by abrol at 25/08/24.
 */
@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onArticleItemClick: (Uri) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SearchContent(
        modifier = modifier,
        state = state,
        onValueChange = { viewModel.onTextChangeEvent(it) },
        onArticleItemClick = onArticleItemClick,
    )
}

@Composable
private fun SearchContent(
    modifier: Modifier = Modifier,
    state: SearchViewModel.UiState,
    listState: LazyListState = rememberLazyListState(),
    onValueChange: (String) -> Unit,
    onArticleItemClick: (Uri) -> Unit,
) {
    val kbdFocusRequester = remember { FocusRequester() }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(kbdFocusRequester)
                .padding(all = 12.dp),
            value = state.uiState.text,
            onValueChange = onValueChange,
        )
        if (state.uiState.isEmpty || state.uiState.articleList.isEmpty()) {
            Image(
                modifier = Modifier.size(size = 200.dp),
                painter = painterResource(id = R.drawable.ic_no_data),
                contentDescription = stringResource(id = R.string.search_title)
            )
            Text(
                text = stringResource(id = R.string.no_data_found),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .testTag(NewsSourceTestTags.LISTINGS_NEWS_SOURCE)
                    .background(Color.LightGray),
                state = listState
            ) {
                items(items = state.uiState.articleList) {
                    ArticleItem(
                        article = { it },
                        onArticleItemClick = onArticleItemClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchContentPreview() {
    NewsAppTheme {
        SearchContent(
            state = SearchViewModel.UiState(
                uiState = SearchStateHolder.UiState(
                    isEmpty = true,
                    articleList = emptyList(),
                    iconResId = R.drawable.ic_search,
                    text = ""
                )
            ),
            onValueChange = {},
            onArticleItemClick = {},
        )
    }
}
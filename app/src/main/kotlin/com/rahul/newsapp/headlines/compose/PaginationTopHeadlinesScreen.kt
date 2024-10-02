package com.rahul.newsapp.headlines.compose

import android.content.res.Resources
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rahul.newsapp.common.IndeterminateCircularIndicator
import com.rahul.newsapp.common.compose.ArticleItem
import com.rahul.newsapp.headlines.stateholder.NetworkConnectivityStateHolder
import com.rahul.newsapp.headlines.stateholder.TopHeadlinesStateHolder
import com.rahul.newsapp.headlines.stateholder.TopHeadlinesViewModel
import com.rahul.newsapp.headlines.utils.TopHeadlinesTestTags
import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.local.entity.Source
import com.rahul.newsapp.theme.NewsAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
internal fun PaginationTopHeadlinesScreen(
    modifier: Modifier = Modifier,
    viewModel: TopHeadlinesViewModel = hiltViewModel(),
    onArticleItemClick: (Uri) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    TopHeadlinesContent(
        modifier = modifier,
        state = state,
        scope = coroutineScope,
        onArticleItemClick = onArticleItemClick,
        onRetryClick = { coroutineScope.launch { viewModel.onRetryClick() } }
    )
}

@Composable
private fun TopHeadlinesContent(
    modifier: Modifier = Modifier,
    state: TopHeadlinesViewModel.UiState,
    resource: Resources = LocalContext.current.resources,
    scope: CoroutineScope,
    listState: LazyListState = rememberLazyListState(),
    onArticleItemClick: (Uri) -> Unit,
    onRetryClick: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag(TopHeadlinesTestTags.SCREEN_ROOT),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = {
            if (state.topHeadlinesState.isLoading) {
                IndeterminateCircularIndicator()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .clearSemantics(state.topHeadlinesState.isLoading)
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
    )

    if (state.networkState.connectedState.not()) {
        state.networkState.errorSnackBar?.let { snackBarState ->
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = resource.getString(snackBarState.message),
                    actionLabel = resource.getString(snackBarState.actionLabel),
                    duration = snackBarState.duration
                ).also { result ->
                    if (result == SnackbarResult.ActionPerformed) {
                        onRetryClick()
                    }
                }
            }
        }
    } else {
        snackbarHostState.currentSnackbarData?.dismiss()
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
                    placeholderList = emptyList()
                ),
                networkState = NetworkConnectivityStateHolder.UiState()
            ),
            onArticleItemClick = {},
            onRetryClick = {},
            scope = rememberCoroutineScope()
        )
    }
}

/**
 * Clear semantics
 *
 * @param loading
 * @return Modifier
 */
@OptIn(ExperimentalComposeUiApi::class)
private fun Modifier.clearSemantics(
    loading: Boolean
): Modifier =
    if (loading) {
        clearAndSetSemantics {
            invisibleToUser()
        }
    } else {
        this
    }

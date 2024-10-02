package com.rahul.newsapp.home.compose

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rahul.newsapp.common.compose.AppTabLargeItem
import com.rahul.newsapp.countries.compose.CountriesScreen
import com.rahul.newsapp.headlines.compose.PaginationTopHeadlinesScreen
import com.rahul.newsapp.home.model.HomeTab
import com.rahul.newsapp.home.model.Type
import com.rahul.newsapp.home.stateholder.HomeStateHolder
import com.rahul.newsapp.home.stateholder.HomeViewModel
import com.rahul.newsapp.home.utils.HomeTestTags
import com.rahul.newsapp.language.compose.LanguagesScreen
import com.rahul.newsapp.search.compose.SearchScreen
import com.rahul.newsapp.source.compose.NewsSourceScreen
import kotlinx.coroutines.launch

/**
 * Created by abrol at 25/08/24.
 */
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onArticleItemClick: (Uri) -> Unit,
    onNewsSourceItemClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(
        modifier = modifier,
        uiState = state,
        onTabSelected = { viewModel.tabSelectedEvent(it) },
        onArticleItemClick = onArticleItemClick,
        onNewsSourceItemClick = onNewsSourceItemClick
    )
}

@Composable
private fun HomeScreenContent(
    modifier: Modifier = Modifier,
    uiState: HomeViewModel.UiState,
    onTabSelected: (Type) -> Unit,
    onArticleItemClick: (Uri) -> Unit,
    onNewsSourceItemClick: (String) -> Unit
) {
    val pagerState: PagerState = rememberPagerState(
        pageCount = { uiState.tabs.tabs.size },
        initialPage = uiState.tabs.selectedTab.ordinal
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 38.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeTabs(pagerState = pagerState, uiState = { uiState.tabs })
        HomePager(
            pagerState = pagerState,
            tabs = { uiState.tabs.tabs },
            onPageSelected = { page -> onTabSelected(page) },
            onArticleItemClick = onArticleItemClick,
            onNewsSourceItemClick = onNewsSourceItemClick
        )
    }
}

@Composable
private fun HomeTabs(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    uiState: () -> HomeStateHolder.UiState
) {
    TabRow(
        modifier = modifier
            .testTag(tag = HomeTestTags.TAB_ROW),
        selectedTabIndex = pagerState.currentPage
    ) {
        val coroutineScope = rememberCoroutineScope()
        uiState().tabs.forEach { item ->
            Tab(
                modifier = Modifier
                    .testTag(tag = item.testTag),
                selected = item.type == uiState().selectedTab,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(item.type.ordinal)
                    }
                },
                content = {
                    AppTabLargeItem(
                        selected = item.type == uiState().selectedTab,
                        text = stringResource(id = item.labelResId),
                        iconResId = item.iconResId
                    )
                }
            )
        }
    }
}

/**
 * The Book screen composable the is in charge of handling internal navigation
 * within the Book screen.
 *
 * @param modifier An ordered, immutable collection of modifier elements that decorate or add behavior
 *                 to Compose UI elements. For example, backgrounds, padding and click event listeners
 *                 decorate or add behavior to rows, text or buttons.
 * @param pagerState The state that is associated with the pager [HorizontalPager]
 * @param tabs The list of [TabRow] items displayed in the screen
 * @param onPageSelected A lambda that is invoked when the pager was swiped and the current pages changes
 */
@Composable
private fun HomePager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    tabs: () -> List<HomeTab>,
    onPageSelected: (Type) -> Unit,
    onArticleItemClick: (Uri) -> Unit,
    onNewsSourceItemClick: (String) -> Unit
) {
    LaunchedEffect(pagerState) {
        // Collect from the a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }
            .collect { pageNum ->
                onPageSelected(tabs()[pageNum].type)
            }
    }

    HorizontalPager(
        modifier = modifier
            .fillMaxSize()
            .testTag(tag = HomeTestTags.NAVIGATION_PAGER),
        state = pagerState
    ) {
        when (it) {
            Type.TOP_HEADLINES.ordinal -> PaginationTopHeadlinesScreen(onArticleItemClick = onArticleItemClick)
            Type.NEWS_SOURCE.ordinal -> NewsSourceScreen(onNewsSourceItemClick = onNewsSourceItemClick)
            Type.COUNTRY.ordinal -> CountriesScreen(onCountriesItemClick = onNewsSourceItemClick)
            Type.LANGUAGE.ordinal -> LanguagesScreen(onLanguagesItemClick = onNewsSourceItemClick)
            Type.SEARCH.ordinal -> SearchScreen(onArticleItemClick = onArticleItemClick)
        }
    }
}

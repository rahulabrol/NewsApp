package com.rahul.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import com.rahul.newsapp.headlines.compose.PaginationTopHeadlinesScreen
import com.rahul.newsapp.home.compose.HomeScreen
import com.rahul.newsapp.navigation.internals.AppNavigationController
import com.rahul.newsapp.navigation.internals.AppNavigationHost
import com.rahul.newsapp.navigation.internals.navigate
import com.rahul.newsapp.navigation.routes.BookType
import com.rahul.newsapp.navigation.routes.Home
import com.rahul.newsapp.navigation.routes.NewsListingById
import com.rahul.newsapp.navigation.routes.NewsSource
import com.rahul.newsapp.navigation.routes.PaginationTopHeadlines
import com.rahul.newsapp.navigation.routes.Test
import com.rahul.newsapp.news.compose.NewsByIdScreen
import com.rahul.newsapp.source.compose.NewsSourceScreen
import com.rahul.newsapp.utils.NEWS_SOURCE
import com.rahul.newsapp.web.CustomTabLauncher
import kotlin.reflect.typeOf

/**
 * The navigation controller for app wide navigation graphs and destinations.
 *
 * @param modifier
 * @param navController
 * @param customTabLauncher
 *
 * Created by abrol at 24/08/24.
 */
@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: AppNavigationController,
    customTabLauncher: CustomTabLauncher,
) {
    AppNavigationHost(
        modifier = modifier,
        navController = navController,
        startDestination = Home,
    ) {
        composable<Home> {
            HomeScreen(
                onArticleItemClick = { customTabLauncher.launchTab(it) },
                onNewsSourceItemClick = {
                    navController.navigate(
                        NewsListingById(
                            test = listOf(
                                Test(
                                    id = it,
                                    type = NEWS_SOURCE,
                                ),
                            ),
                        ),
                    )
                },
            )
        }
        composable<PaginationTopHeadlines> {
            PaginationTopHeadlinesScreen(
                onArticleItemClick = { customTabLauncher.launchTab(it) },
            )
        }
        composable<NewsSource> {
            NewsSourceScreen(onNewsSourceItemClick = {
                navController.navigate(
                    NewsListingById(
                        test = listOf(
                            Test(
                                id = it,
                                type = NEWS_SOURCE,
                            ),
                        ),
                    ),
                )
            })
        }
        composable<NewsListingById>(typeMap = mapOf(typeOf<List<Test>>() to BookType)) {
            NewsByIdScreen(
                onArticleItemClick = { customTabLauncher.launchTab(it) },
            )
        }
    }
}

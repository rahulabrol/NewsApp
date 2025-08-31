package com.rahul.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import com.rahul.newsapp.headlines.compose.PaginationTopHeadlinesScreen
import com.rahul.newsapp.home.compose.HomeScreen
import com.rahul.newsapp.navigation.internals.AppNavigationController
import com.rahul.newsapp.navigation.internals.AppNavigationHost
import com.rahul.newsapp.navigation.internals.navigate
import com.rahul.newsapp.navigation.routes.HomeRoute
import com.rahul.newsapp.navigation.routes.NewsListingByIdRoute
import com.rahul.newsapp.navigation.routes.NewsSourceRoute
import com.rahul.newsapp.navigation.routes.PaginationTopHeadlinesRoute
import com.rahul.newsapp.navigation.routes.TestRoute
import com.rahul.newsapp.navigation.routes.TestRouteListNavType
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
@Suppress("FunctionName")
@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: AppNavigationController,
    customTabLauncher: CustomTabLauncher,
) {
    AppNavigationHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeRoute,
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onArticleItemClick = { customTabLauncher.launchTab(it) },
                onNewsSourceItemClick = {
                    navController.navigate(
                        NewsListingByIdRoute(
                            test =
                                listOf(
                                    TestRoute(
                                        id = it,
                                        type = NEWS_SOURCE,
                                    ),
                                ),
                        ),
                    )
                },
            )
        }
        composable<PaginationTopHeadlinesRoute> {
            PaginationTopHeadlinesScreen(
                onArticleItemClick = { customTabLauncher.launchTab(it) },
            )
        }
        composable<NewsSourceRoute> {
            NewsSourceScreen(onNewsSourceItemClick = {
                navController.navigate(
                    NewsListingByIdRoute(
                        test =
                            listOf(
                                TestRoute(
                                    id = it,
                                    type = NEWS_SOURCE,
                                ),
                            ),
                    ),
                )
            })
        }
        composable<NewsListingByIdRoute>(typeMap = mapOf(typeOf<List<TestRoute>>() to TestRouteListNavType)) {
            NewsByIdScreen(
                onArticleItemClick = { customTabLauncher.launchTab(it) },
            )
        }
    }
}

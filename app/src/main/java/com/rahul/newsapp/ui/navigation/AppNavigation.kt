package com.rahul.newsapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import com.rahul.newsapp.ui.home.HomeScreen
import com.rahul.newsapp.ui.navigation.internals.AppNavigationController
import com.rahul.newsapp.ui.navigation.internals.AppNavigationHost
import com.rahul.newsapp.ui.navigation.routes.Countries
import com.rahul.newsapp.ui.navigation.routes.Home
import com.rahul.newsapp.ui.navigation.routes.Languages
import com.rahul.newsapp.ui.navigation.routes.NewsSource
import com.rahul.newsapp.ui.navigation.routes.PaginationTopHeadlines
import com.rahul.newsapp.ui.navigation.routes.Search
import com.rahul.newsapp.ui.navigation.routes.TopHeadlines

/**
 * The navigation controller for app wide navigation graphs and destinations.
 *
 * @param modifier
 * @param navController
 *
 * Created by abrol at 24/08/24.
 */
@Composable
internal fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: AppNavigationController
) {
    AppNavigationHost(
        modifier = modifier,
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                launchPaginationTopHeadlineScreen = {},
                launchTopHeadlineScreen = {},
                launchNewsSourcesScreen = {},
                launchCountriesScreen = {},
                launchLanguagesScreen = {},
                launchSearchScreen = {}
            )
        }
        composable<PaginationTopHeadlines> { }
        composable<TopHeadlines> { }
        composable<NewsSource> { }
        composable<Countries> { }
        composable<Languages> { }
        composable<Search> { }
    }
}
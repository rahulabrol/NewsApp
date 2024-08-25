package com.rahul.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.composable
import com.rahul.newsapp.countries.compose.CountriesScreen
import com.rahul.newsapp.home.compose.HomeScreen
import com.rahul.newsapp.language.compose.LanguagesScreen
import com.rahul.newsapp.navigation.internals.AppNavigationController
import com.rahul.newsapp.navigation.internals.AppNavigationHost
import com.rahul.newsapp.navigation.internals.navigate
import com.rahul.newsapp.navigation.internals.popBackStack
import com.rahul.newsapp.navigation.routes.Countries
import com.rahul.newsapp.navigation.routes.Home
import com.rahul.newsapp.navigation.routes.Languages
import com.rahul.newsapp.navigation.routes.NewsSource
import com.rahul.newsapp.navigation.routes.PaginationTopHeadlines
import com.rahul.newsapp.navigation.routes.Search
import com.rahul.newsapp.navigation.routes.TopHeadlines
import com.rahul.newsapp.news_source.compose.NewsSourceScreen
import com.rahul.newsapp.search.compose.SearchScreen
import com.rahul.newsapp.top_headlines.compose.PaginationTopHeadlinesScreen
import com.rahul.newsapp.top_headlines.compose.TopHeadlinesScreen
import com.rahul.newsapp.utils.COUNTRIES
import com.rahul.newsapp.utils.LANGUAGES
import com.rahul.newsapp.utils.NEWS_SOURCE
import com.rahul.newsapp.utils.SEARCH
import com.rahul.newsapp.utils.TOP_HEADLINES

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
                launchPaginationTopHeadlineScreen = { navController.navigate(PaginationTopHeadlines) },
                launchTopHeadlineScreen = { navController.navigate(TopHeadlines) },
                launchNewsSourcesScreen = { navController.navigate(NewsSource) },
                launchCountriesScreen = { navController.navigate(Countries) },
                launchLanguagesScreen = { navController.navigate(Languages) },
                launchSearchScreen = { navController.navigate(Search) }
            )
        }
        composable<PaginationTopHeadlines> {
            PaginationTopHeadlinesScreen(
                screenName = { TOP_HEADLINES },
                onBackPress = { navController.popBackStack() }
            )
        }
        composable<TopHeadlines> {
            TopHeadlinesScreen(screenName = { TOP_HEADLINES },
                onBackPress = { navController.popBackStack() })
        }
        composable<NewsSource> {
            NewsSourceScreen(screenName = { NEWS_SOURCE },
                onBackPress = { navController.popBackStack() })
        }
        composable<Countries> {
            CountriesScreen(screenName = { COUNTRIES },
                onBackPress = { navController.popBackStack() })
        }
        composable<Languages> {
            LanguagesScreen(screenName = { LANGUAGES },
                onBackPress = { navController.popBackStack() })
        }
        composable<Search> {
            SearchScreen(
                screenName = { SEARCH },
                onBackPress = { navController.popBackStack() })
        }
    }
}
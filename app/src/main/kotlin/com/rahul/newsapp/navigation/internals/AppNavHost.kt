package com.rahul.newsapp.navigation.internals

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost

/**
 * Created by abrol at 24/08/24.
 */

/**
 * @return the default enter animation transition
 */
@ExperimentalAnimationApi
internal fun AnimatedContentTransitionScope<*>.defaultEnterTransition() =
    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)

/**
 * @return the default exit animation transition
 */
@ExperimentalAnimationApi
internal fun AnimatedContentTransitionScope<*>.defaultExitTransition() =
    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start)

/**
 * @return the default pop enter animation transition
 */
@ExperimentalAnimationApi
internal fun AnimatedContentTransitionScope<*>.defaultPopEnterTransition() =
    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End)

/**
 * @return the default pop exit animation transition
 */
@ExperimentalAnimationApi
internal fun AnimatedContentTransitionScope<*>.defaultPopExitTransition() =
    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End)


@OptIn(ExperimentalAnimationApi::class)
@Composable
internal fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navController: AppNavigationController,
    startDestination: Any,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = navController.asNavHostController(),
        startDestination = startDestination,
        enterTransition = { defaultEnterTransition() },
        exitTransition = { defaultExitTransition() },
        popEnterTransition = { defaultPopEnterTransition() },
        popExitTransition = { defaultPopExitTransition() },
        modifier = modifier,
        builder = builder
    )
}
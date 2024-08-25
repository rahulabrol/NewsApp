package com.rahul.newsapp.ui.navigation.internals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.Navigator
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.rememberNavController
import com.rahul.newsapp.ui.navigation.routes.ScreenGraph

/**
 * Wraps a NavController, which offers additional APIs for use by a NavHost to connect the NavController
 * to external dependencies.
 * Apps should generally not construct controllers, instead obtain a relevant controller directly
 * from a navigation host via NavHost.getNavController or by using one of the utility methods on the
 * Navigation class.
 *
 * @property _controller
 *
 * Created by abrol at 24/08/24.
 */
data class AppNavigationController(
    private val _controller: NavHostController
) {
    /**
     * @return the wrapped [NavHostController]
     */
    internal fun asNavHostController() = _controller
}

/**
 * Navigate to a Screen
 *
 * @param screenGraph
 */
fun AppNavigationController.navigate(screenGraph: ScreenGraph) {
    asNavHostController().navigate(route = screenGraph)
}

/**
 * Attempts to navigate up in the navigation hierarchy. Suitable for when the
 * user presses the "Up" button marked with a left (or start)-facing arrow in the upper left
 * (or starting) corner of the app UI.
 *
 * The intended behavior of Up differs from [Back][popBackStack] when the user
 * did not reach the current destination from the application's own task. e.g. if the user
 * is viewing a document or link in the current app in an activity hosted on another app's
 * task where the user clicked the link. In this case the current activity (determined by the
 * context used to create this NavController) will be [finished][Activity.finish] and
 * the user will be taken to an appropriate destination in this app on its own task.
 *
 * @return true if navigation was successful, false otherwise
 */
fun AppNavigationController.navigateUp() = asNavHostController().navigateUp()

/**
 * Attempts to pop the controller's back stack. Analogous to when the user presses the system Back
 * button when the associated navigation host has focus.
 *
 * @return true if the stack was popped at least once and the user has been navigated
 * to another destination, false otherwise
 */
fun AppNavigationController.popBackStack() {
    when (asNavHostController().currentBackStackEntry?.lifecycle?.currentState) {
        Lifecycle.State.RESUMED -> {
            asNavHostController().popBackStack()
        }
        Lifecycle.State.DESTROYED,
        Lifecycle.State.INITIALIZED,
        Lifecycle.State.CREATED,
        Lifecycle.State.STARTED,
        null -> {} // no-op, not valid state to pop back stack
        else -> {}
    }
}

/**
 * Creates a [AppNavigationController] wrapping a NavHostController that handles the adding of the
 * [ComposeNavigator] and [DialogNavigator]. Additional [Navigator] instances can be passed through
 * [navigators] to be applied to the returned NavController. Note that each [Navigator] must be separately
 * remembered before being passed in here: any changes to those inputs will cause the
 * NavController to be recreated.
 *
 * @param navigators
 * @return the AppNavigationController
 *
 * @see NavHost
 */
@Composable
fun rememberAppAnimatedNavController(
    vararg navigators: Navigator<out NavDestination>
): AppNavigationController {
    val animatedNavigator = remember { ComposeNavigator() }
    return AppNavigationController(
        _controller = rememberNavController(
            animatedNavigator, *navigators
        )
    )
}
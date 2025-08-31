package com.rahul.newsapp.navigation.internals

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AppNavigationControllerTest {
    @MockK(relaxed = true)
    lateinit var navHostController: NavHostController

    @MockK(relaxed = true)
    lateinit var backStackEntry: NavBackStackEntry

    @MockK(relaxed = true)
    lateinit var lifecycle: Lifecycle

    private lateinit var controller: AppNavigationController

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        controller = AppNavigationController(navHostController)
    }

    @Test
    fun `navigateUp delegates to NavHostController`() {
        every { navHostController.navigateUp() } returns true
        val result = controller.navigateUp()
        assertTrue(result)
        verify(exactly = 1) { navHostController.navigateUp() }
    }

    @Test
    fun `popBackStack is called only when current state is RESUMED`() {
        // Not resumed
        every { backStackEntry.lifecycle } returns lifecycle
        every { lifecycle.currentState } returns Lifecycle.State.STARTED
        every { navHostController.currentBackStackEntry } returns backStackEntry

        controller.popBackStack()
        verify(exactly = 0) { navHostController.popBackStack() }

        // Resumed
        every { lifecycle.currentState } returns Lifecycle.State.RESUMED
        controller.popBackStack()
        verify(exactly = 1) { navHostController.popBackStack() }
    }
}

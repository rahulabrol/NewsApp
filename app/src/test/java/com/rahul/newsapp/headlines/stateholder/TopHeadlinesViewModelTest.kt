package com.rahul.newsapp.headlines.stateholder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.rahul.newsapp.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by abrol at 07/09/24.
 */
class TopHeadlinesViewModelTest {
    /**
     * A rule necessary to test Jetpack architecture components.
     *
     * @see [InstantTaskExecutorRule]
     */
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    /**
     * A rule necessary to handle the TestCoroutineDispatcher for testing
     *
     * @see [CoroutineTestRule]
     */
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var coroutineRule = CoroutineTestRule()

    @MockK
    lateinit var topHeadlinesStateHolder: TopHeadlinesStateHolder

    @MockK
    lateinit var networkConnectivityStateHolder: NetworkConnectivityStateHolder

    private lateinit var viewModel: TopHeadlinesViewModel

    private val expectedList = TopHeadlinesStateHolder.UiState(
        isLoading = true,
        articleList = emptyList(),
        placeholderList = emptyList()
    )

    /**
     * Create a new view model before each test to verify state from it's initial state
     */
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        coEvery { topHeadlinesStateHolder.state } returns flowOf(expectedList)

        coEvery { networkConnectivityStateHolder.state } returns flowOf(
            NetworkConnectivityStateHolder.UiState(
                errorSnackBar = null,
                connectedState = false
            )
        )

        viewModel = TopHeadlinesViewModel(
            networkConnectivityStateHolder = networkConnectivityStateHolder,
            topHeadlinesState = topHeadlinesStateHolder
        )
    }

    @Test
    fun verifyInitialState() = runTest {
        viewModel.state.test {
            val uiState = awaitItem()

            // verify List
            val actualList = uiState.topHeadlinesState
            assertEquals(actualList, expectedList)
        }
    }

    @Test
    fun testOnNetworkRetryEvent() = runTest {
        viewModel.onRetryClick()
        coVerify(exactly = 1) {
            networkConnectivityStateHolder.onRetryClick()
        }
    }

    @Test
    fun testOnTopHeadlinesRetryEvent() = runTest {
        viewModel.onRetryClick()
        coVerify(exactly = 1) {
            topHeadlinesStateHolder.fetchTopHeadlinesOnRetry()
        }
    }
}

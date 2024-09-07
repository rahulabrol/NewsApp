package com.rahul.newsapp.top_headlines.stateholder

import app.cash.turbine.test
import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.local.entity.Source
import com.rahul.newsapp.top_headlines.domain.TopHeadlinesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Test the Top Headlines state
 *
 * Created by abrol at 07/09/24.
 */
class TopHeadlinesStateHolderTest {

    /**
     * The state object to be tested
     */
    private lateinit var stateHolder: TopHeadlinesStateHolder

    @MockK(relaxed = true)
    lateinit var topHeadlinesUseCase: TopHeadlinesUseCase

    /**
     * 1. Initialize MockK
     * 4. Instantiate a new instance of the [TopHeadlinesStateHolder] before each test
     */
    @Before
    fun setup() {
        MockKAnnotations.init(this)

        stateHolder = TopHeadlinesStateHolder(topHeadlinesUseCase)
    }

    @Test
    fun verifyInitialState() = runTest {
        val uiState = stateHolder.initialState
        assertTrue(uiState.isLoading)
        assertNotNull(uiState.articleList)
        assertNotNull(uiState.placeholderList)
    }

    @Test
    fun verifySuccessState() = runTest {
        coEvery { topHeadlinesUseCase("us") } returns flowOf(fakeTopHeadlinesList())
        stateHolder.state.test {
            val state = awaitItem()
            assertNotNull(state.articleList)
            assertFalse(state.isLoading)
        }
    }

    private fun fakeTopHeadlinesList(): List<Article> = listOf(
        Article(
            articleId = 1,
            title = "Test",
            description = "This is test description.",
            url = "empty",
            imageUrl = "empty",
            country = "us",
            language = "ar",
            source = Source(sourceId = "sourceId", name = "Source test")
        )
    )
}
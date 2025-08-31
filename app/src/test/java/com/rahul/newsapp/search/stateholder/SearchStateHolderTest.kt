package com.rahul.newsapp.search.stateholder

import app.cash.turbine.test
import com.rahul.newsapp.local.entity.LocalArticle
import com.rahul.newsapp.local.entity.LocalSource
import com.rahul.newsapp.search.domain.SearchUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.OffsetDateTime

class SearchStateHolderTest {
    @MockK
    lateinit var searchUseCase: SearchUseCase

    private lateinit var holder: SearchStateHolder

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        holder = SearchStateHolder(searchUseCase)
    }

    @Test
    fun `initial state is empty with default icon and text`() =
        runTest {
            val state = holder.initialState
            assertTrue(state.isEmpty)
            assertEquals("", state.text)
            assertFalse(state.isLoading)
            assertEquals(0, state.articleList.size)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onTextChange shorter than threshold only updates text`() =
        runTest {
            holder.state.test {
                // initial state
                awaitItem()

                holder.onTextChange("ab")
                val s = awaitItem()
                assertEquals("ab", s.text)
                assertFalse(s.isLoading)
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `onTextChange above threshold triggers search and updates list`() =
        runTest {
            val results =
                listOf(
                    LocalArticle(
                        title = "T",
                        description = "D",
                        url = "u",
                        imageUrl = "i",
                        publishedDate = OffsetDateTime.now(),
                        localSource = LocalSource("id", "name"),
                    ),
                )
            coEvery { searchUseCase("news") } returns flowOf(results)

            holder.state.test {
                // initial state
                awaitItem()

                holder.onTextChange("news")
                // Let delay and search finish
                advanceUntilIdle()

                // After text updated
                val s1 = awaitItem()
                // After isLoading true
                val s2 = awaitItem()
                // After results
                val s3 = awaitItem()
                assertEquals("news", s3.text)
                assertFalse(s3.isLoading)
                assertTrue(s3.articleList.isNotEmpty())
                cancelAndIgnoreRemainingEvents()
            }
        }
}

package com.rahul.newsapp.search.stateholder

import app.cash.turbine.test
import com.rahul.newsapp.search.domain.SearchUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class SearchStateHolderErrorTest {
    @MockK
    lateinit var searchUseCase: SearchUseCase

    private lateinit var holder: SearchStateHolder

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        holder = SearchStateHolder(searchUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `error during search clears loading`() =
        runTest {
            coEvery { searchUseCase("boom") } returns flow { throw RuntimeException("fail") }

            holder.state.test {
                awaitItem() // initial
                holder.onTextChange("boom")
                advanceUntilIdle()
                // After text, after loading true, after catch -> loading false again
                val s1 = awaitItem()
                val s2 = awaitItem()
                val s3 = awaitItem()
                assertFalse(s3.isLoading)
                cancelAndIgnoreRemainingEvents()
            }
        }
}

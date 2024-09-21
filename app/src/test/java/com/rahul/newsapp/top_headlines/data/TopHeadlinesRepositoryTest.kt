package com.rahul.newsapp.top_headlines.data

import com.rahul.newsapp.top_headlines.data.model.TopHeadlinesEntity
import com.rahul.newsapp.top_headlines.data.source.TopHeadlinesDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockkClass
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

/**
 * Created by abrol at 07/09/24.
 */
internal class TopHeadlinesRepositoryTest {
    @MockK
    lateinit var dataSource: TopHeadlinesDataSource

    private lateinit var repository: TopHeadlinesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        repository = TopHeadlinesRepository(dataSource)
    }

    @Test
    fun verifySuccess() = runTest {
        coEvery { dataSource.topHeadlines("us") } returns Result.success(
            mockkClass(TopHeadlinesEntity::class, relaxed = true)
        )
        assertNotNull(dataSource.topHeadlines("us"))
    }

    @Test
    fun verifyFailure() = runTest {
        coEvery { dataSource.topHeadlines("us") } returns Result.failure(
            Throwable()
        )
        assert(dataSource.topHeadlines("us").isFailure)
    }

}
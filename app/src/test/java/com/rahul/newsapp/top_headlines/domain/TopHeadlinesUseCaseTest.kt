package com.rahul.newsapp.top_headlines.domain

import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.local.entity.Source
import com.rahul.newsapp.top_headlines.data.TopHeadlinesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by abrol at 07/09/24.
 */
class TopHeadlinesUseCaseTest {
    @MockK
    lateinit var repository: TopHeadlinesRepository

    private lateinit var useCase: TopHeadlinesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        useCase = TopHeadlinesUseCase(repository)
    }

    @Test
    fun whenGetSuccess() = runTest {
        coEvery { repository.topHeadlines("us") } returns Result.success(fakeTopHeadlinesList())

        val result = useCase("us").single()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun whenGetFailed() = runTest {
        coEvery { repository.topHeadlines("us") } returns Result.failure(
            Exception()
        )

        val result = useCase("us").single()
        assertTrue(result.isEmpty())
    }

    @Test
    fun verifyTopHeadlinesMapper() = runTest {
        val topHeadlinesEntity = fakeTopHeadlinesList()
        coEvery { repository.topHeadlines("us") } returns Result.success(
            topHeadlinesEntity
        )

        val result = useCase("us").single().getOrNull(0)

        assertEquals(topHeadlinesEntity[0].title, result?.title)
        assertEquals(topHeadlinesEntity[0].description, result?.description)
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
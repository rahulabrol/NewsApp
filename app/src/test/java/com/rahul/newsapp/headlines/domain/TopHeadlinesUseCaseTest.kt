package com.rahul.newsapp.headlines.domain

import com.rahul.newsapp.headlines.data.TopHeadlinesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
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
    fun whenGetSuccess() =
        runTest {
            coEvery { repository.topHeadlines("us", 1) } returns Unit
            val params = TopHeadlinesParams("us", 1)
            val result = useCase(params).single()
            assert(result == Unit)
        }
}

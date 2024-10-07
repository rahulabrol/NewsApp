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
    fun whenGetSuccess() = runTest {
        coEvery { repository.topHeadlines("us") } returns Unit

        val result = useCase("us").single()
        assert(result == Unit)
    }
}

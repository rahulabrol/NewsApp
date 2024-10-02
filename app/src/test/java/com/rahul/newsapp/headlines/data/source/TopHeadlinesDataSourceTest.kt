package com.rahul.newsapp.headlines.data.source

import com.rahul.newsapp.headlines.data.model.TopHeadlinesNetworkEntity
import com.rahul.newsapp.networking.NetworkService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkClass
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by abrol at 07/09/24.
 */
class TopHeadlinesDataSourceTest {
    @MockK
    lateinit var client: NetworkService

    private lateinit var dataSource: TopHeadlinesDataSourceImpl

    @RelaxedMockK
    private var mockResponse = mockkClass(TopHeadlinesNetworkEntity::class, relaxed = true)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        dataSource = TopHeadlinesDataSourceImpl(client)

        coEvery { client.getTopHeadlines("us") } returns mockResponse
    }

    @Test
    fun if_client_returning_top_headlines() = runTest {
        val result = dataSource.topHeadlines("us")

        coVerify { client.getTopHeadlines("us") }

        Assert.assertTrue(result.isSuccess)

        Assert.assertFalse(result.isFailure)

        Assert.assertEquals(mockResponse, result.getOrNull())
        Assert.assertEquals(
            mockResponse.articles.isNotEmpty(),
            result.getOrNull()?.articles?.isNotEmpty()
        )
    }
}

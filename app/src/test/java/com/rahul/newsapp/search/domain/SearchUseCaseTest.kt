package com.rahul.newsapp.search.domain

import com.rahul.newsapp.local.entity.LocalArticle
import com.rahul.newsapp.local.entity.LocalSource
import com.rahul.newsapp.search.data.SearchRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.OffsetDateTime

class SearchUseCaseTest {
    @MockK
    lateinit var repo: SearchRepository

    private lateinit var useCase: SearchUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        useCase = SearchUseCase(repo)
    }

    @Test
    fun `returns repository result`() =
        runTest {
            val list =
                listOf(
                    LocalArticle(
                        title = "t",
                        description = "d",
                        url = "u",
                        imageUrl = "i",
                        publishedDate = OffsetDateTime.now(),
                        localSource = LocalSource(),
                    ),
                )
            coEvery { repo.search("kotlin") } returns Result.success(list)
            val result = useCase("kotlin").first()
            assertEquals(list, result)
        }

    @Test
    fun `returns empty list when repository fails`() =
        runTest {
            coEvery { repo.search("fail") } returns Result.failure(Exception("boom"))
            val result = useCase("fail").first()
            assertEquals(emptyList<LocalArticle>(), result)
        }
}

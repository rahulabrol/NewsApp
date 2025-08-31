package com.rahul.newsapp.local.entity

import com.rahul.newsapp.headlines.data.model.ArticlesNetworkEntity
import com.rahul.newsapp.headlines.data.model.SourceNetworkEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.OffsetDateTime

class LocalArticleMappingTest {
    @Test
    fun `ArticlesNetworkEntity maps to LocalArticle correctly`() {
        val network =
            ArticlesNetworkEntity(
                source = SourceNetworkEntity(id = "bbc", name = "BBC"),
                title = "Title",
                description = "Desc",
                url = "http://example.com",
                imageUrl = "http://img",
                publishedAt = "",
            )
        val published = OffsetDateTime.now()
        val local = network.toLocalArticleEntity(published)

        assertEquals("Title", local.title)
        assertEquals("Desc", local.description)
        assertEquals("http://example.com", local.url)
        assertEquals("http://img", local.imageUrl)
        assertEquals(published, local.publishedDate)
        assertEquals("BBC", local.localSource.name)
        assertEquals("bbc", local.localSource.sourceId)
    }
}

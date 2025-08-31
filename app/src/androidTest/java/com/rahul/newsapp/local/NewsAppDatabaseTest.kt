package com.rahul.newsapp.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.rahul.newsapp.local.dao.TopHeadlinesDao
import com.rahul.newsapp.local.entity.LocalArticle
import com.rahul.newsapp.local.entity.LocalSource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.OffsetDateTime
import java.time.ZoneOffset

@HiltAndroidTest
class NewsAppDatabaseTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    private lateinit var db: NewsAppDatabase
    private lateinit var dao: TopHeadlinesDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db =
            Room
                .inMemoryDatabaseBuilder(context, NewsAppDatabase::class.java)
                .allowMainThreadQueries() // acceptable for tests
                .build()
        dao = db.topHeadlinesDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insert_and_query_articles_flow_emits_values() =
        runTest {
            val a1 = sampleArticle(id = 0, title = "A1")
            val a2 = sampleArticle(id = 0, title = "A2")

            dao.insertArticles(listOf(a1, a2))

            dao.getAllCachedArticlesFlow().test {
                val list = awaitItem()
                assertEquals(2, list.size)
                assertTrue(list.any { it.title == "A1" })
                assertTrue(list.any { it.title == "A2" })
                cancelAndIgnoreRemainingEvents()
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun get_article_by_id_flow_emits_item() =
        runTest {
            val a1 = sampleArticle(id = 0, title = "A1")
            val a2 = sampleArticle(id = 0, title = "A2")
            dao.insertArticles(listOf(a1, a2))

            // Fetch all to read generated IDs, then query by one id
            val all = dao.getAllCachedArticlesFlow().first()
            val targetId = all.first().articleId
            assertTrue(targetId != 0)

            dao.getCachedArticleByIdFlow(targetId).test {
                val item = awaitItem()
                assertNotNull(item)
                assertEquals(targetId, item?.articleId)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun clear_then_insert_replaces_content() =
        runTest {
            val a1 = sampleArticle(title = "Old")
            dao.insertArticles(listOf(a1))
            // ensure 1 item present
            assertEquals(1, dao.getAllCachedArticlesFlow().first().size)

            // clear and insert new
            dao.clearCachedArticles()
            val a2 = sampleArticle(title = "New")
            dao.insertArticles(listOf(a2))

            val list = dao.getAllCachedArticlesFlow().first()
            assertEquals(1, list.size)
            assertEquals("New", list.first().title)
        }

    private fun sampleArticle(
        id: Int = 0,
        title: String = "T",
    ): LocalArticle {
        val whenPublished = OffsetDateTime.of(2024, 9, 7, 12, 0, 0, 0, ZoneOffset.UTC)
        return LocalArticle(
            articleId = id,
            title = title,
            description = "Desc",
            url = "http://example.com/$title",
            imageUrl = "http://img/$title.png",
            publishedDate = whenPublished, // exercises OffsetDateTimeConverter
            localSource = LocalSource(sourceId = "id", name = "Name"),
        )
    }
}

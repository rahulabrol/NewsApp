package com.rahul.newsapp.headlines.data

import android.annotation.SuppressLint
import com.rahul.newsapp.headlines.data.source.TopHeadlinesDataSource
import com.rahul.newsapp.local.dao.TopHeadlinesDao
import com.rahul.newsapp.local.entity.LocalArticle
import com.rahul.newsapp.local.entity.toLocalArticleEntity
import kotlinx.coroutines.flow.Flow
import java.time.OffsetDateTime
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
class TopHeadlinesRepository @Inject constructor(
    private val topHeadlinesDataSource: TopHeadlinesDataSource,
    private val topHeadlinesDao: TopHeadlinesDao
) {
    /**
     * Type Ahead Feedback call
     *
     */
    @SuppressLint("NewApi")
    @Throws
    internal suspend fun topHeadlines(country: String) {
        // Fetch the top headlines from network
        val response = topHeadlinesDataSource.topHeadlines(country = country).getOrThrow()
        val cachedArticles = response.articles.map {
            it.toLocalArticleEntity(
                publishedDate = OffsetDateTime.parse(it.publishedAt)
            )
        }
        // Cache them locally by clearing the previous cache and inserting the new articles
        topHeadlinesDao.clearCachedArticles()
        topHeadlinesDao.insertArticles(cachedArticles)
    }

    internal fun localArticle(): Flow<List<LocalArticle>> {
        return topHeadlinesDao.getAllCachedArticlesFlow()
    }
}

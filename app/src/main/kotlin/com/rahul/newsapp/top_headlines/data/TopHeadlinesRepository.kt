package com.rahul.newsapp.top_headlines.data

import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.top_headlines.data.mapper.toArticles
import com.rahul.newsapp.top_headlines.data.source.TopHeadlinesDataSource
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
class TopHeadlinesRepository @Inject constructor(
    private val topHeadlinesDataSource: TopHeadlinesDataSource,
//    private val topHeadlinesDao: TopHeadlinesDao,
) {
    /**
     * Type Ahead Feedback call
     *
     */
    suspend fun topHeadlines(country: String): Result<List<Article>> {
//        val offlineHeadlines = topHeadlinesDao.getAllTopHeadlinesArticle(country = country).first()
//        return if (offlineHeadlines.isEmpty()) {
        return topHeadlinesDataSource.topHeadlines(country = country)
            .map { it.articles.toArticles() }
//        } else {
//            Result.success(offlineHeadlines)
//        }
    }

}

package com.rahul.newsapp.headlines.data

import com.rahul.newsapp.headlines.data.mapper.toArticleList
import com.rahul.newsapp.headlines.data.source.TopHeadlinesDataSource
import com.rahul.newsapp.local.entity.Article
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
class TopHeadlinesRepository @Inject constructor(
    private val topHeadlinesDataSource: TopHeadlinesDataSource
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
            .map { it.articles.toArticleList() }
//        } else {
//            Result.success(offlineHeadlines)
//        }
    }
}

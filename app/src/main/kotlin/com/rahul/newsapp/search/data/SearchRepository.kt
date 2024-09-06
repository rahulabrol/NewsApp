package com.rahul.newsapp.search.data

import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.search.data.source.SearchDataSource
import com.rahul.newsapp.top_headlines.data.mapper.toArticles
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
class SearchRepository @Inject constructor(
    private val searchDataSource: SearchDataSource
) {
    suspend fun search(query: String): Result<List<Article>> {
        return searchDataSource.search(query = query).map { it.articles.toArticles() }
    }
}
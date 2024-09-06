package com.rahul.newsapp.search.data.source

import com.rahul.newsapp.top_headlines.data.model.TopHeadlinesEntity

/**
 * Created by abrol at 06/09/24.
 */
interface SearchDataSource {
    suspend fun search(query: String): Result<TopHeadlinesEntity>
}
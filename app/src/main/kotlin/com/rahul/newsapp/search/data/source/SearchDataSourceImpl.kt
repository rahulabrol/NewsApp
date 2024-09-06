package com.rahul.newsapp.search.data.source

import com.rahul.newsapp.networking.NetworkService
import com.rahul.newsapp.top_headlines.data.model.TopHeadlinesEntity
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
class SearchDataSourceImpl @Inject constructor(private val networkService: NetworkService) :
    SearchDataSource {
    override suspend fun search(query: String): Result<TopHeadlinesEntity> =
        runCatching { networkService.getNewsByQueries(queries = query) }
}
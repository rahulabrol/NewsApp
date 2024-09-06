package com.rahul.newsapp.top_headlines.data.source

import com.rahul.newsapp.local.dao.TopHeadlinesDao
import com.rahul.newsapp.networking.NetworkService
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
class TopHeadlinesDataSourceImpl @Inject constructor(
    private val networkService: NetworkService,
    private val topHeadlinesDao: TopHeadlinesDao
) : TopHeadlinesDataSource {

    override suspend fun topHeadlines(country: String) = runCatching {
        networkService.getTopHeadlines(country = country)
    }
}
package com.rahul.newsapp.news_source.data.source

import com.rahul.newsapp.networking.NetworkService
import com.rahul.newsapp.news_source.data.model.NewsSourceEntity
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
class NewsSourceDataSourceImpl @Inject constructor(private val networkService: NetworkService) :
    NewsSourceDataSource {

    override suspend fun newsSource(): Result<NewsSourceEntity> =
        runCatching {
            networkService.getNewsSources()
        }
}
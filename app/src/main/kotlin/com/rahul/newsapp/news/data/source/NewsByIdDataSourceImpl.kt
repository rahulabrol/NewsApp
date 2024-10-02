package com.rahul.newsapp.news.data.source

import com.rahul.newsapp.headlines.data.model.TopHeadlinesNetworkEntity
import com.rahul.newsapp.networking.NetworkService
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
class NewsByIdDataSourceImpl @Inject constructor(private val networkService: NetworkService) :
    NewsByIdDataSource {

    override suspend fun newsBySourceId(sourceId: String): Result<TopHeadlinesNetworkEntity> =
        runCatching {
            networkService.getNewsBySources(sources = sourceId)
        }
}

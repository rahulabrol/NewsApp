package com.rahul.newsapp.news.data.source

import com.rahul.newsapp.headlines.data.model.TopHeadlinesNetworkEntity

/**
 * Created by abrol at 06/09/24.
 */
interface NewsByIdDataSource {
    suspend fun newsBySourceId(sourceId: String): Result<TopHeadlinesNetworkEntity>
}

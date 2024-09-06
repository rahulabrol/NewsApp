package com.rahul.newsapp.news.data.source

import com.rahul.newsapp.top_headlines.data.model.TopHeadlinesEntity

/**
 * Created by abrol at 06/09/24.
 */
interface NewsByIdDataSource {
    suspend fun newsBySourceId(sourceId: String): Result<TopHeadlinesEntity>
}
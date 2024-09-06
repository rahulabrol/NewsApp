package com.rahul.newsapp.news_source.data.source

import com.rahul.newsapp.news_source.data.model.NewsSourceEntity


/**
 * Created by abrol at 06/09/24.
 */
interface NewsSourceDataSource {
    suspend fun newsSource(): Result<NewsSourceEntity>
}
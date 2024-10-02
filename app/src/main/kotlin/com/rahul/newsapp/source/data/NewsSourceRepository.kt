package com.rahul.newsapp.source.data

import com.rahul.newsapp.source.data.model.NewsSourceEntity
import com.rahul.newsapp.source.data.source.NewsSourceDataSource
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
class NewsSourceRepository @Inject constructor(
    private val newsSourceDataSource: NewsSourceDataSource
) {
    /**
     * Type Ahead Feedback call
     *
     */
    suspend fun newsSource(): Result<NewsSourceEntity> {
        return newsSourceDataSource.newsSource()
    }
}

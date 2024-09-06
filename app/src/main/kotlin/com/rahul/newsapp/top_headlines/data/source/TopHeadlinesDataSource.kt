package com.rahul.newsapp.top_headlines.data.source

import com.rahul.newsapp.top_headlines.data.model.TopHeadlinesEntity

/**
 * Top Headlines API Data Source.
 *
 * Created by abrol at 25/08/24.
 */
interface TopHeadlinesDataSource {

    /**
     * Top headlines API for news listing.
     *
     */
    suspend fun topHeadlines(country: String): Result<TopHeadlinesEntity>
}
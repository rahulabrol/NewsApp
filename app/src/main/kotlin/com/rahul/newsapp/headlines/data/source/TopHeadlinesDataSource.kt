package com.rahul.newsapp.headlines.data.source

import com.rahul.newsapp.headlines.data.model.TopHeadlinesNetworkEntity

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
    suspend fun topHeadlines(country: String): Result<TopHeadlinesNetworkEntity>
}

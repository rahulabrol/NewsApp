package com.rahul.newsapp.headlines.domain

import com.rahul.newsapp.base.ResultUseCase
import com.rahul.newsapp.headlines.data.TopHeadlinesRepository
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
class TopHeadlinesUseCase @Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository,
) : ResultUseCase<TopHeadlinesParams, Unit>() {
    override suspend fun doWork(params: TopHeadlinesParams) {
        topHeadlinesRepository.topHeadlines(country = params.country, page = params.page)
    }
}

/**
 * Top headlines params
 *
 * @property country which country news we want
 * @property page which page data we want to fetch
 * @constructor Create empty Top headlines params
 */
data class TopHeadlinesParams(
    val country: String,
    val page: Int,
)

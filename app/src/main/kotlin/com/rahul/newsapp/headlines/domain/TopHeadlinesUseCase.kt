package com.rahul.newsapp.headlines.domain

import com.rahul.newsapp.base.ResultUseCase
import com.rahul.newsapp.headlines.data.TopHeadlinesRepository
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
class TopHeadlinesUseCase @Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository
) : ResultUseCase<String, Unit>() {
    override suspend fun doWork(params: String) {
        topHeadlinesRepository.topHeadlines(country = params)
    }
}

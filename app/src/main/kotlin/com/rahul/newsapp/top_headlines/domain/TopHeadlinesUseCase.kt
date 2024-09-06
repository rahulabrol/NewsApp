package com.rahul.newsapp.top_headlines.domain

import com.rahul.newsapp.base.ResultUseCase
import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.top_headlines.data.TopHeadlinesRepository
import javax.inject.Inject

/**
 * Created by abrol at 25/08/24.
 */
class TopHeadlinesUseCase @Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository
) : ResultUseCase<String, List<Article>>() {
    override suspend fun doWork(params: String): List<Article> {
        return topHeadlinesRepository.topHeadlines(country = params).getOrDefault(emptyList())
    }
}

package com.rahul.newsapp.search.domain

import com.rahul.newsapp.base.ResultUseCase
import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.search.data.SearchRepository
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : ResultUseCase<String, List<Article>>() {
    override suspend fun doWork(params: String): List<Article> {
        return searchRepository.search(query = params).getOrDefault(emptyList())
    }
}
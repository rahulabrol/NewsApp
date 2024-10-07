package com.rahul.newsapp.search.domain

import com.rahul.newsapp.base.ResultUseCase
import com.rahul.newsapp.local.entity.LocalArticle
import com.rahul.newsapp.search.data.SearchRepository
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : ResultUseCase<String, List<LocalArticle>>() {
    override suspend fun doWork(params: String): List<LocalArticle> {
        return searchRepository.search(query = params).getOrDefault(emptyList())
    }
}

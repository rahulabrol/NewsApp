package com.rahul.newsapp.news.domain

import com.rahul.newsapp.base.ResultUseCase
import com.rahul.newsapp.local.entity.LocalArticle
import com.rahul.newsapp.news.data.NewsByIdRepository
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
class NewsByIdUseCase @Inject constructor(
    private val newsByIdRepository: NewsByIdRepository
) : ResultUseCase<String, List<LocalArticle>>() {
    @Throws
    override suspend fun doWork(params: String): List<LocalArticle> {
        return newsByIdRepository.newsBySourceId(sourceId = params).getOrDefault(emptyList())
    }
}

package com.rahul.newsapp.news_source.domain

import com.rahul.newsapp.base.ResultUseCase
import com.rahul.newsapp.news_source.data.NewsSourceRepository
import com.rahul.newsapp.news_source.domain.model.Source
import com.rahul.newsapp.top_headlines.data.model.APINewsSourceEntity
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
class NewsSourceUseCase @Inject constructor(
    private val newsSourceRepository: NewsSourceRepository
) : ResultUseCase<Unit, List<Source>>() {
    @Throws
    override suspend fun doWork(params: Unit): List<Source> {
        return newsSourceRepository.newsSource().getOrThrow().newsSource.map { it.toNewsSource() }
    }
}

private fun APINewsSourceEntity.toNewsSource() = Source(id = this.id, name = this.name)
package com.rahul.newsapp.headlines.domain

import com.rahul.newsapp.base.FlowUseCase
import com.rahul.newsapp.headlines.data.TopHeadlinesRepository
import com.rahul.newsapp.local.entity.LocalArticle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by abrol at 05/10/24.
 */
class LocalArticleUseCase @Inject constructor(
    private val topHeadlinesRepository: TopHeadlinesRepository
) : FlowUseCase<Unit, List<LocalArticle>>() {

    override fun createObservable(params: Unit): Flow<List<LocalArticle>> {
        return topHeadlinesRepository.localArticle()
    }
}

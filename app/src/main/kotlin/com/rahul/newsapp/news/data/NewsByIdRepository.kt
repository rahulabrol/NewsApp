package com.rahul.newsapp.news.data


import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.news.data.source.NewsByIdDataSource
import com.rahul.newsapp.top_headlines.data.mapper.toArticles
import javax.inject.Inject

/**
 * Created by abrol at 06/09/24.
 */
class NewsByIdRepository @Inject constructor(
    private val newsByIdDataSource: NewsByIdDataSource,
) {
    suspend fun newsBySourceId(sourceId: String): Result<List<Article>> {
        return newsByIdDataSource.newsBySourceId(sourceId = sourceId)
            .map { it.articles.toArticles() }
    }

}

package com.rahul.newsapp.top_headlines.data.mapper

import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.local.entity.Source
import com.rahul.newsapp.top_headlines.data.model.ArticleEntity
import com.rahul.newsapp.top_headlines.data.model.SourceEntity

/**
 * Created by abrol at 25/08/24.
 */

internal fun List<ArticleEntity>.toArticles() = map {
    it.toArticle()
}

private fun ArticleEntity.toArticle() = Article(
    title = this.title,
    description = this.description.orEmpty(),
    url = this.url,
    imageUrl = this.imageUrl.orEmpty(),
    source = this.source.toSource()
)

private fun SourceEntity.toSource() = Source(
    sourceId = this.id.orEmpty(),
    name = this.name
)

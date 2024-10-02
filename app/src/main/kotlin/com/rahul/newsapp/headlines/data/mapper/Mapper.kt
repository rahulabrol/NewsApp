package com.rahul.newsapp.headlines.data.mapper

import com.rahul.newsapp.headlines.data.model.ArticlesNetworkEntity
import com.rahul.newsapp.headlines.data.model.SourceNetworkEntity
import com.rahul.newsapp.local.entity.Article
import com.rahul.newsapp.local.entity.Source

/**
 * Created by abrol at 25/08/24.
 */

internal fun List<ArticlesNetworkEntity>.toArticleList() = map {
    it.toArticle()
}

private fun ArticlesNetworkEntity.toArticle() = Article(
    title = this.title.orEmpty(),
    description = this.description.orEmpty(),
    url = this.url,
    imageUrl = this.imageUrl.orEmpty(),
    source = this.source.toSource()
)

private fun SourceNetworkEntity.toSource() = Source(
    sourceId = this.id.orEmpty(),
    name = this.name
)

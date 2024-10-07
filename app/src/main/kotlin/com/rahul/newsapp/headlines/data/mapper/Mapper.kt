package com.rahul.newsapp.headlines.data.mapper

import android.annotation.SuppressLint
import com.rahul.newsapp.headlines.data.model.ArticlesNetworkEntity
import com.rahul.newsapp.headlines.data.model.SourceNetworkEntity
import com.rahul.newsapp.local.entity.LocalArticle
import com.rahul.newsapp.local.entity.LocalSource
import java.time.OffsetDateTime

/**
 * Created by abrol at 25/08/24.
 */

internal fun List<ArticlesNetworkEntity>.toArticleList() = map {
    it.toArticle()
}

@SuppressLint("NewApi")
private fun ArticlesNetworkEntity.toArticle() = LocalArticle(
    title = this.title.orEmpty(),
    description = this.description.orEmpty(),
    url = this.url,
    imageUrl = this.imageUrl.orEmpty(),
    localSource = this.source.toSource(),
    publishedDate = OffsetDateTime.parse(this.publishedAt)
)

private fun SourceNetworkEntity.toSource() = LocalSource(
    sourceId = this.id.orEmpty(),
    name = this.name
)

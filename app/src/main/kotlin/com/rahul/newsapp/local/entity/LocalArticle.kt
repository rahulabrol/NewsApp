package com.rahul.newsapp.local.entity

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rahul.newsapp.headlines.data.model.ArticlesNetworkEntity
import com.rahul.newsapp.headlines.data.model.SourceNetworkEntity
import java.time.OffsetDateTime

/**
 * Created by abrol at 25/08/24.
 */
@Entity(tableName = "cached_articles")
data class LocalArticle(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "article_id")
    var articleId: Int = 0, // Make id optional with default value 0
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "url") val url: String = "",
    @ColumnInfo(name = "urlToImage") val imageUrl: String = "",
    @Embedded
   /* @ColumnInfo(name = "published_date")*/ val publishedDate: OffsetDateTime?,
    @Embedded var localSource: LocalSource
) {
    companion object {
        /**
         * Placeholder, used for Shimmer UI effect
         */
        @SuppressLint("NewApi")
        val placeholder = LocalArticle(
            articleId = Math.random().toInt(),
            imageUrl = "",
            title = "This is a test title for placeholder",
            description = "This is a test description used for the placeholder.",
            url = "",
            publishedDate = OffsetDateTime.now(),
            localSource = LocalSource(sourceId = "", name = "This is a Test Source.")

        )
    }
}

internal fun ArticlesNetworkEntity.toLocalArticleEntity(publishedDate: OffsetDateTime): LocalArticle =
    LocalArticle(
        title = this.title.orEmpty(),
        description = this.description.orEmpty(),
        url = this.url,
        imageUrl = this.imageUrl.orEmpty(),
        publishedDate = publishedDate,
        localSource = this.source.toLocalSource()
    )

private fun SourceNetworkEntity.toLocalSource(): LocalSource {
    return LocalSource(name = this.name, sourceId = this.id.orEmpty())
}

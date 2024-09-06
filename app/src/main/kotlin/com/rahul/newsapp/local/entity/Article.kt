package com.rahul.newsapp.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by abrol at 25/08/24.
 */
@Entity(tableName = "TopHeadlinesArticle")
data class Article(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "article_id") var articleId: Int = 0,
    @ColumnInfo(name = "title") val title: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "url") val url: String = "",
    @ColumnInfo(name = "urlToImage") val imageUrl: String = "",
    @ColumnInfo(name = "country") val country: String = "",
    @ColumnInfo(name = "language") val language: String = "",
    @Embedded var source: Source
) {
    companion object {
        /**
         * Placeholder, used for Shimmer UI effect
         */
        val placeholder = Article(
            articleId = Math.random().toInt(),
            imageUrl = "",
            title = "This is a test title for placeholder",
            description = "This is a test description used for the placeholder.",
            url = "",
            country = "",
            language = "",
            source = Source(sourceId = "", name = "This is a Test Source.")

        )
    }
}

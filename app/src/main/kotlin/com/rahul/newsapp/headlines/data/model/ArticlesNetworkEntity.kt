package com.rahul.newsapp.headlines.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

/**
 * Created by abrol at 25/08/24.
 */
data class ArticlesNetworkEntity(
    @SerializedName("title") val title: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("urlToImage") val imageUrl: String? = "",
    @SerialName(value = "publishedAt") val publishedAt: String? = "",
    @SerializedName("source") val source: SourceNetworkEntity
)

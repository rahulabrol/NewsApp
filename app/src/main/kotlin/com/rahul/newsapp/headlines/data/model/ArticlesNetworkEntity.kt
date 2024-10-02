package com.rahul.newsapp.headlines.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abrol at 25/08/24.
 */
data class ArticlesNetworkEntity(
    @SerializedName("title") val title: String? = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("urlToImage") val imageUrl: String? = "",
    @SerializedName("source") val source: SourceNetworkEntity
)

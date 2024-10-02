package com.rahul.newsapp.headlines.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abrol at 25/08/24.
 */
data class TopHeadlinesNetworkEntity(
    @SerializedName("status") val status: String = "",
    @SerializedName("totalResults") val count: Int = 0,
    @SerializedName("articles") val articles: List<ArticlesNetworkEntity> = emptyList()
)

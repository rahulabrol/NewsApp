package com.rahul.newsapp.top_headlines.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Created by abrol at 25/08/24.
 */
data class TopHeadlinesEntity(
    @SerializedName("status") val status: String = "",
    @SerializedName("totalResults") val count: Int = 0,
    @SerializedName("articles") val articles: List<ArticlesEntity> = emptyList(),
)

package com.rahul.newsapp.news_source.data.model

import com.google.gson.annotations.SerializedName
import com.rahul.newsapp.top_headlines.data.model.APINewsSourceEntity

/**
 * Created by abrol at 06/09/24.
 */
data class NewsSourceEntity(
    @SerializedName("status") val status: String = "",
    @SerializedName("sources") val newsSource: List<APINewsSourceEntity> = emptyList(),
)

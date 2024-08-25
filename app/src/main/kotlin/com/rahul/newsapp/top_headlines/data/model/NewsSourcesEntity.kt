package com.rahul.newsapp.top_headlines.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abrol at 25/08/24.
 */
data class NewsSourcesEntity(
    @SerializedName("status") val status: String = "",
    @SerializedName("sources") val newsSource: List<APINewsSourceEntity> = arrayListOf(),
)

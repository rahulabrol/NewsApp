package com.rahul.newsapp.source.data.model

import com.google.gson.annotations.SerializedName
import com.rahul.newsapp.headlines.data.model.APINewsSourceEntity

/**
 * Created by abrol at 06/09/24.
 */
data class NewsSourceEntity(
    @SerializedName("status") val status: String = "",
    @SerializedName("sources") val newsSource: List<APINewsSourceEntity> = emptyList()
)

package com.rahul.newsapp.top_headlines.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abrol at 25/08/24.
 */
data class APINewsSourceEntity(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("category") val category: String = "",
    @SerializedName("language") val language: String = "",
    @SerializedName("country") val country: String = "",
)

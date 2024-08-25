package com.rahul.newsapp.top_headlines.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abrol at 25/08/24.
 */
data class SourceEntity(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String = "",
)

package com.rahul.newsapp.headlines.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by abrol at 25/08/24.
 */
data class SourceNetworkEntity(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String = ""
)

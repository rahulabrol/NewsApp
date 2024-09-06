package com.rahul.newsapp.navigation.routes

import kotlinx.serialization.Serializable

/**
 * Created by abrol at 24/08/24.
 */
sealed interface ScreenGraph

@Serializable
data object Home : ScreenGraph

@Serializable
data object PaginationTopHeadlines : ScreenGraph

@Serializable
data object TopHeadlines : ScreenGraph

@Serializable
data object NewsSource : ScreenGraph

@Serializable
data class NewsListingById(
    val id: String,
    val type: String
) : ScreenGraph

@Serializable
data object Countries : ScreenGraph

@Serializable
data object Languages : ScreenGraph

@Serializable
data object Search : ScreenGraph
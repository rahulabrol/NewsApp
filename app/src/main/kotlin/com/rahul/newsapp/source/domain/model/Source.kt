package com.rahul.newsapp.source.domain.model

/**
 * Created by abrol at 25/08/24.
 */
data class Source(
    val id: String? = null,
    val name: String = ""
) {
    companion object {
        /**
         * Placeholder, used for Shimmer UI effect
         */
        val placeholder = Source(
            id = "",
            name = "This is a test name"
        )
    }
}

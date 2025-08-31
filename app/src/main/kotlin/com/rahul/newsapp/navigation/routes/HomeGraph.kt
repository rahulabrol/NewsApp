package com.rahul.newsapp.navigation.routes

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.ArrayList

/**
 * Created by abrol at 24/08/24.
 */
@Serializable
data object HomeRoute

@Serializable
data object PaginationTopHeadlinesRoute

@Serializable
data object NewsSourceRoute

@Serializable
data class NewsListingByIdRoute(
    val test: List<TestRoute>,
)

@Serializable
@Parcelize
data class TestRoute(
    val id: String,
    val type: String,
) : Parcelable

@Serializable
data object CountriesRoute

@Serializable
data object LanguagesRoute

@Serializable
data object SearchRoute

/**
 * Custom NavType for handling a list of [TestRoute] objects in navigation.
 *
 * This NavType allows you to pass a list of [TestRoute] objects as a navigation argument.
 * It handles serialization and deserialization of the list using JSON and ensures proper
 * encoding for URI compatibility.
 */
val TestRouteListNavType =
    object : NavType<List<TestRoute>>(
        isNullableAllowed = false,
    ) {
        override fun get(
            bundle: Bundle,
            key: String,
        ): List<TestRoute>? =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelableArrayList<TestRoute>(key, TestRoute::class.java)
            } else {
                @Suppress("DEPRECATION")
                bundle.getParcelableArrayList<TestRoute>(key) as List<TestRoute>
            }

        override fun parseValue(value: String): List<TestRoute> {
            // Navigation takes care of decoding the string
            // before passing it to parseValue()
            return Json.decodeFromString<List<TestRoute>>(value)
        }

        override fun serializeAsValue(value: List<TestRoute>): String {
            // Serialized values must always be Uri encoded
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(
            bundle: Bundle,
            key: String,
            value: List<TestRoute>,
        ) {
            bundle.putParcelableArrayList(key, value as ArrayList<out Parcelable?>?)
        }
    }

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
data object Home

@Serializable
data object PaginationTopHeadlines

@Serializable
data object TopHeadlines

@Serializable
data object NewsSource

@Serializable
data class NewsListingById(
    val test: List<Test>,
)

@Serializable
@Parcelize
data class Test(
    val id: String,
    val type: String,
) : Parcelable

@Serializable
data object Countries

@Serializable
data object Languages

@Serializable
data object Search

val BookType = object : NavType<List<Test>>(
    isNullableAllowed = false,
) {
    override fun get(bundle: Bundle, key: String): List<Test>? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelableArrayList<Test>(key, Test::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): List<Test> {
        // Navigation takes care of decoding the string
        // before passing it to parseValue()
        return Json.decodeFromString<List<Test>>(value)
    }

    override fun serializeAsValue(value: List<Test>): String {
        // Serialized values must always be Uri encoded
        return Uri.encode(Json.encodeToString(value))
    }

    override fun put(bundle: Bundle, key: String, value: List<Test>) {
        bundle.putParcelableArrayList(key, value as ArrayList<out Parcelable?>?)
    }
}

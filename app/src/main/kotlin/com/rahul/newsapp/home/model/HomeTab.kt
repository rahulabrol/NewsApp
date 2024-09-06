package com.rahul.newsapp.home.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * A model data class that represents a single tab item.
 *
 * @property type The screen that the item/tab represents
 * @property iconResId The icon resource id to be displayed in the bottom navigation item
 * @property labelResId The text resource id to be displayed under the icon in a bottom navigation item
 * @property contentDescriptionResId The content description resource id to be describe the icon,
 *                                   or null if one isn't needed.
 * @property testTag
 *
 * Created by abrol at 06/09/24.
 */
data class HomeTab(
    val type: Type,
    @StringRes val labelResId: Int,
    @StringRes val contentDescriptionResId: Int?,
    @DrawableRes val iconResId: Int,
    val testTag: String
)
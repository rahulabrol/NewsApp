package com.rahul.newsapp.common.compose.internal

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rahul.newsapp.R

object IconDefaults {
    private const val DEFAULT_ICON_BACKGROUND_SIZE = 48
    private const val DEFAULT_LARGE_ICON_SIZE = 32
    private const val DEFAULT_SELECTED_ICON_SIZE = 28

    val IconSize: Dp
        @Composable
        get() = dimensionResource(id = R.dimen.icon_size)

    val SelectedIconSize: Dp
        @Composable
        get() = DEFAULT_SELECTED_ICON_SIZE.dp

    val LargeIconSize: Dp
        @Composable
        get() = DEFAULT_LARGE_ICON_SIZE.dp

    val IconBackgroundSize: Dp
        @Composable
        get() = DEFAULT_ICON_BACKGROUND_SIZE.dp
}

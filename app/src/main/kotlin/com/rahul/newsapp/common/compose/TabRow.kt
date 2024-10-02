package com.rahul.newsapp.common.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rahul.newsapp.common.compose.internal.AppTabDefaults

/**
 * Created by abrol at 06/09/24.
 */

/**
 * The content of an [AppTab].
 *
 * @param modifier An optional [Modifier] for this tab
 * @param selected Whether this tab is selected or not
 * @param text The text label for this tab
 * @param iconResId The icon resource id for this tab
 */
@Composable
fun AppTabLargeItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    text: String,
    @DrawableRes iconResId: Int
) {
    Column(
        modifier = modifier
            .padding(
                top = 8.dp,
                bottom = 8.dp
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTabLargeItemIcon(
            selected = selected,
            iconResId = iconResId
        )
        Spacer(
            modifier = Modifier.height(
                height = 4.dp
            )
        )
        AppTabText(
            text = text,
            selected = selected,
            color = AppTabDefaults.LargeItemTextColor,
            style = AppTabDefaults.LargeItemTextStyle
        )
    }
}

/**
 * The Image that is the Icon of an [AppTabLargeItem].
 *
 * @param modifier An option [Modifier] for this Image Icon
 * @param selected Whether the tab this Image Icon is in, is selected
 * @param colorNormal The normal color of the Image Icon background
 * @param colorSelected The color of a selected Image Icon background
 * @param largeIconSize The size of the overall Icon
 * @param largeIconBackgroundSize The size of the Image Icon within the tab
 * @param iconResId The Image Icon resource id.
 */
@Composable
fun AppTabLargeItemIcon(
    modifier: Modifier = Modifier,
    selected: Boolean,
    colorNormal: Color = AppTabDefaults.ItemIconBackgroundColorNormal,
    colorSelected: Color = AppTabDefaults.ItemIconBackgroundColorSelected,
    largeIconBackgroundSize: Dp = AppTabDefaults.LargeIconBackgroundSize,
    largeIconSize: Dp = AppTabDefaults.LargeIconSize,
    shape: Shape = AppTabDefaults.IconBackgroundShape,
    iconResId: Int
) {
    Box(
        modifier = modifier
            .size(size = largeIconBackgroundSize)
            .background(
                color = if (selected) colorSelected else colorNormal,
                shape = shape
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(size = largeIconSize),
            painter = painterResource(id = iconResId),
            contentDescription = null
        )
    }
}

/**
 * The text label for an or [AppTabItem] or [AppTabLargeItem]
 *
 * @param modifier An optional [Modifier] for this text
 * @param selected Whether the tab is selected
 * @param text The text of this label
 * @param color [Color] to apply to the text.
 * @param style Style configuration for the text such as color, font, line height etc.
 */
@Composable
private fun AppTabText(
    modifier: Modifier = Modifier,
    selected: Boolean,
    text: String,
    color: Color,
    style: TextStyle
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = style,
        fontWeight = if (selected) FontWeight.Bold else null
    )
}

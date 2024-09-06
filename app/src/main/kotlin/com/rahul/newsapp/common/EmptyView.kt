package com.rahul.newsapp.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rahul.newsapp.R

/**
 * Created by abrol at 07/09/24.
 */
@Composable
fun EmptyView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(size = 200.dp),
            painter = painterResource(id = R.drawable.ic_no_data),
            contentDescription = stringResource(id = R.string.search_title)
        )
        Text(
            text = stringResource(id = R.string.no_data_found),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        )
    }
}
package com.rahul.newsapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Created by abrol at 25/08/24.
 */

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    launchPaginationTopHeadlineScreen: () -> Unit,
    launchTopHeadlineScreen: () -> Unit,
    launchNewsSourcesScreen: () -> Unit,
    launchCountriesScreen: () -> Unit,
    launchLanguagesScreen: () -> Unit,
    launchSearchScreen: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = launchPaginationTopHeadlineScreen) {
            Text(text = "Pagination Top Headlines")
        }
        Button(onClick = launchTopHeadlineScreen) {
            Text(text = "Top Headlines")
        }
        Button(onClick = launchNewsSourcesScreen) {
            Text(text = "News Sources")
        }
        Button(onClick = launchCountriesScreen) {
            Text(text = "Countries")
        }
        Button(onClick = launchLanguagesScreen) {
            Text(text = "Languages")
        }
        Button(onClick = launchSearchScreen) {
            Text(text = "Search")
        }
    }
}
package com.rahul.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.rahul.newsapp.navigation.AppNavigation
import com.rahul.newsapp.navigation.internals.rememberAppAnimatedNavController
import com.rahul.newsapp.theme.NewsAppTheme
import com.rahul.newsapp.web.CustomTabLauncher
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), CustomTabLauncher {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(
                        navController = rememberAppAnimatedNavController(),
                        customTabLauncher = this
                    )
                }
            }
        }
    }
}

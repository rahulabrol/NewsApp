package com.rahul.newsapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.rahul.newsapp.MainActivity
import org.junit.Rule
import org.junit.Test

@dagger.hilt.android.testing.HiltAndroidTest
class SearchScreenUiTest {
    @get:Rule(order = 0)
    val hiltRule =
        dagger.hilt.android.testing
            .HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun app_loads_and_pager_is_present() {
        // Smoke check: app launches and pager exists
        com.rahul.newsapp.home.utils.HomeTestTags.apply {
            composeRule.onNodeWithTag(NAVIGATION_PAGER).assertIsDisplayed()
        }
    }
}

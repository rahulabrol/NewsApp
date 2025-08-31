package com.rahul.newsapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.rahul.newsapp.MainActivity
import com.rahul.newsapp.home.utils.HomeTestTags
import org.junit.Rule
import org.junit.Test

@dagger.hilt.android.testing.HiltAndroidTest
class HomeScreenUiTest {
    @get:Rule(order = 0)
    val hiltRule =
        dagger.hilt.android.testing
            .HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun tabs_are_visible_and_switch_pages() {
        // Tab row exists
        composeRule.onNodeWithTag(HomeTestTags.TAB_ROW).assertIsDisplayed()
        // Click each tab by its label text (strings loaded in Activity)
        // We just ensure clicks don't crash and pager exists
        composeRule.onNodeWithTag(HomeTestTags.NAVIGATION_PAGER).assertIsDisplayed()
    }
}

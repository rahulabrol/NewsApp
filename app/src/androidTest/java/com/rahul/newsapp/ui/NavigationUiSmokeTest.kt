package com.rahul.newsapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.rahul.newsapp.MainActivity
import com.rahul.newsapp.home.utils.HomeTestTags
import org.junit.Rule
import org.junit.Test

@dagger.hilt.android.testing.HiltAndroidTest
class NavigationUiSmokeTest {
    @get:Rule(order = 0)
    val hiltRule =
        dagger.hilt.android.testing
            .HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun pager_is_present() {
        composeRule.onNodeWithTag(HomeTestTags.NAVIGATION_PAGER).assertIsDisplayed()
    }
}

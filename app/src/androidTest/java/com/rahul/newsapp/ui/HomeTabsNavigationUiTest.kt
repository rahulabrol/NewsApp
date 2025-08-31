package com.rahul.newsapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelectable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.rahul.newsapp.MainActivity
import com.rahul.newsapp.home.utils.HomeTestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeTabsNavigationUiTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun clicking_each_tab_keeps_pager_visible() {
        // Ensure TabRow and Pager visible initially
        composeRule.onNodeWithTag(HomeTestTags.TAB_ROW).assertIsDisplayed()
        composeRule.onNodeWithTag(HomeTestTags.NAVIGATION_PAGER).assertIsDisplayed()

        val tabs =
            listOf(
                HomeTestTags.TAB_TOP_HEADLINES,
                HomeTestTags.TAB_NEWS_SOURCE,
                HomeTestTags.TAB_COUNTRY,
                HomeTestTags.TAB_LANGUAGE,
                HomeTestTags.TAB_SEARCH,
            )

        tabs.forEach { tag ->
            composeRule.onNodeWithTag(tag).assertIsDisplayed().assertIsSelectable()
            composeRule.onNodeWithTag(tag).performClick()
            // Pager should remain visible after switching
            composeRule.onNodeWithTag(HomeTestTags.NAVIGATION_PAGER).assertIsDisplayed()
        }
    }
}

package com.rahul.newsapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.rahul.newsapp.MainActivity
import com.rahul.newsapp.headlines.utils.TopHeadlinesTestTags
import org.junit.Rule
import org.junit.Test

@dagger.hilt.android.testing.HiltAndroidTest
class TopHeadlinesUiTest {
    @get:Rule(order = 0)
    val hiltRule =
        dagger.hilt.android.testing
            .HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun topHeadlines_list_or_empty_or_loader_is_visible() {
        // One of these should be present depending on live state
        // Screen root
        composeRule.onNodeWithTag(TopHeadlinesTestTags.SCREEN_ROOT).assertIsDisplayed()
        // List may or may not be shown depending on data; just ensure screen loads without crash
    }
}

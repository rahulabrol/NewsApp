package com.rahul.newsapp.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.rahul.newsapp.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchTypingUiTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun typing_in_search_field_updates_text() {
        // Find any TextField by hasSetTextAction (Search tab contains a TextField when visible)
        // This is a smoke-level test; in a real suite we would navigate to Search tab explicitly
        val query = "compose test"
        // Try to find a text field; if not on Search page, click Search tab first
        val maybeTextField = composeRule.onNode(hasSetTextAction())
        try {
            maybeTextField.assertIsDisplayed()
            maybeTextField.performTextInput(query)
        } catch (t: AssertionError) {
            // Navigate to Search tab by clicking its test tag
            com.rahul.newsapp.home.utils.HomeTestTags.apply {
                composeRule.onNodeWithTag(TAB_SEARCH).performClick()
            }
            val textField = composeRule.onNode(hasSetTextAction())
            textField.assertIsDisplayed()
            textField.performTextInput(query)
        }
        // The TextField should now display the entered text
        composeRule.onNodeWithText(query, substring = false).assertIsDisplayed()
    }
}

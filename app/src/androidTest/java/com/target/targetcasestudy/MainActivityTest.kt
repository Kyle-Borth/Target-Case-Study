package com.target.targetcasestudy

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.target.targetcasestudy.ui.utility.TestTags
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun listScreenDisplayed() {
        val title = composeTestRule.activity.getString(R.string.list)
        composeTestRule.onNodeWithText(title).assertIsDisplayed()

        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithTag(TestTags.DEAL_LIST_ITEM).fetchSemanticsNodes().isNotEmpty()
        }
    }

    @Test
    fun detailsDisplayed() {
        // Wait until the deals list has been composed
        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithTag(TestTags.DEAL_LIST_ITEM).fetchSemanticsNodes().isNotEmpty()
        }

        // Select the first deal
        composeTestRule.onAllNodesWithTag(TestTags.DEAL_LIST_ITEM).onFirst().performClick()

        // Check title
        val title = composeTestRule.activity.getString(R.string.details)
        composeTestRule.onNodeWithText(title).assertIsDisplayed()

        // Wait for details to load, then check for existence of Add to cart button
        val addToCartText = composeTestRule.activity.getString(R.string.add_to_cart)
        composeTestRule.waitUntil {
            composeTestRule.onAllNodesWithText(addToCartText).fetchSemanticsNodes().size == 1
        }

    }

}
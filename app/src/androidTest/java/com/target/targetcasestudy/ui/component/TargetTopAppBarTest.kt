package com.target.targetcasestudy.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.target.targetcasestudy.R
import com.target.targetcasestudy.UiTestActivity
import com.target.targetcasestudy.ui.theme.TargetTheme
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

private const val TITLE = "TITLE"

class TargetTopAppBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<UiTestActivity>()

    @Test
    fun titleIsDisplayed() {
        val state = TopBarState(title = TITLE)

        composeTestRule.setContent {
            TargetTheme {
                TargetTopAppBar(state = state)
            }
        }

        composeTestRule.onNodeWithText(TITLE).assertIsDisplayed()
    }

    @Test
    fun backButtonNotDisplayed() {
        val state = TopBarState(title = TITLE)

        composeTestRule.setContent {
            TargetTheme {
                TargetTopAppBar(state = state)
            }
        }

        val contentDescription = composeTestRule.activity.getString(R.string.navigate_back)
        composeTestRule.onNodeWithContentDescription(contentDescription).assertIsNotDisplayed()
    }

    @Test
    fun backButtonDisplayedAndClickable() {
        var backSelected = false
        val state = TopBarState(title = TITLE, onBackSelected = { backSelected = true })

        composeTestRule.setContent {
            TargetTheme {
                TargetTopAppBar(state = state)
            }
        }

        val contentDescription = composeTestRule.activity.getString(R.string.navigate_back)
        composeTestRule.onNodeWithContentDescription(contentDescription).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(contentDescription).performClick()

        Assert.assertTrue(backSelected)
    }

}
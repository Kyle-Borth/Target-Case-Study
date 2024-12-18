package com.target.targetcasestudy.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.target.targetcasestudy.R
import com.target.targetcasestudy.UiTestActivity
import com.target.targetcasestudy.ui.theme.TargetTheme
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class NetworkErrorTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<UiTestActivity>()

    @Test
    fun informativeTextIsDisplayed() {
        composeTestRule.setContent {
            TargetTheme {
                NetworkError(onRetry = {})
            }
        }

        val text = composeTestRule.activity.getString(R.string.network_error)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun retryExistsAndClickable() {
        var retrySelected = false

        composeTestRule.setContent {
            TargetTheme {
                NetworkError(onRetry = { retrySelected = true })
            }
        }

        val text = composeTestRule.activity.getString(R.string.try_again)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
        composeTestRule.onNodeWithText(text).performClick()

        Assert.assertTrue(retrySelected)
    }

}
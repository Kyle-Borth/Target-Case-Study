package com.target.targetcasestudy.ui.screen

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.target.targetcasestudy.Generators
import com.target.targetcasestudy.R
import com.target.targetcasestudy.UiTestActivity
import com.target.targetcasestudy.ui.theme.TargetTheme
import com.target.targetcasestudy.ui.utility.TestTags
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class DealListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<UiTestActivity>()

    @Test
    fun progressIndicatorWhileLoading() {
        composeTestRule.setContent {
            TargetTheme {
                DealListScreen(deals = emptyList(), isLoading = true, onDealSelected = {}, onRetryDownload = {})
            }
        }

        composeTestRule.onNodeWithTag(TestTags.PROGRESS_INDICATOR).assertIsDisplayed()
    }

    @Test
    fun listDisplaysSummaryData() {
        val deal = Generators.dealItem(id = 22, regularPriceInCents = 5000, salePriceInCents = 4000)

        val regPriceText = composeTestRule.activity.getString(R.string.reg_price, deal.regularPrice.displayString)
        val inAisleText = composeTestRule.activity.getString(R.string.in_aisle, deal.aisle)

        composeTestRule.setContent {
            TargetTheme {
                DealListScreen(deals = listOf(deal), isLoading = false, onDealSelected = {}, onRetryDownload = {})
            }
        }

        composeTestRule.onAllNodesWithTag(TestTags.DEAL_LIST_ITEM).assertCountEquals(1)
        composeTestRule.onNodeWithText(deal.salePrice.displayString).assertIsDisplayed()
        composeTestRule.onNodeWithText(regPriceText).assertIsDisplayed()
        composeTestRule.onNodeWithText(deal.fulfillment).assertIsDisplayed()
        composeTestRule.onNodeWithText(deal.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(deal.availability).assertIsDisplayed()
        composeTestRule.onNodeWithText(inAisleText).assertIsDisplayed()
    }

    @Test
    fun dealItemsAreClickable() {
        val deal = Generators.dealItem(id = 23, regularPriceInCents = 6000, salePriceInCents = 5000)
        var selectedDealId = -1

        composeTestRule.setContent {
            TargetTheme {
                DealListScreen(
                    deals = listOf(deal),
                    isLoading = false,
                    onDealSelected = { selectedDealId = it },
                    onRetryDownload = {}
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.DEAL_LIST_ITEM).performClick()

        Assert.assertEquals(deal.id, selectedDealId)
    }

    @Test
    fun networkErrorWhenNoDealsAndIsNotLoading() {
        composeTestRule.setContent {
            TargetTheme {
                DealListScreen(deals = emptyList(), isLoading = false, onDealSelected = {}, onRetryDownload = {})
            }
        }

        val text = composeTestRule.activity.getString(R.string.network_error)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun networkErrorRetryExistsAndClickable() {
        var retrySelected = false

        composeTestRule.setContent {
            TargetTheme {
                DealListScreen(
                    deals = emptyList(),
                    isLoading = false,
                    onDealSelected = {},
                    onRetryDownload = { retrySelected = true }
                )
            }
        }

        val text = composeTestRule.activity.getString(R.string.try_again)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
        composeTestRule.onNodeWithText(text).performClick()

        Assert.assertTrue(retrySelected)
    }

}

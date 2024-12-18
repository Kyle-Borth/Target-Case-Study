package com.target.targetcasestudy.ui.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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

class DealDetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<UiTestActivity>()

    @Test
    fun progressIndicatorWhileLoading() {
        composeTestRule.setContent {
            TargetTheme {
                DealDetailsScreen(dealItem = null, isLoading = true, onAddToCart = {}, onRetryDownload = {})
            }
        }

        composeTestRule.onNodeWithTag(TestTags.PROGRESS_INDICATOR).assertIsDisplayed()
    }

    @Test
    fun displayDealDetails() {
        val deal = Generators.dealItem(id = 22, regularPriceInCents = 5000, salePriceInCents = 4000)

        val regPriceText = composeTestRule.activity.getString(R.string.reg_price, deal.regularPrice.displayString)
        val prodDetailsText = composeTestRule.activity.getString(R.string.product_details)

        composeTestRule.setContent {
            TargetTheme {
                DealDetailsScreen(dealItem = deal, isLoading = false, onAddToCart = {}, onRetryDownload = {})
            }
        }

        composeTestRule.onNodeWithText(deal.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(deal.salePrice.displayString).assertIsDisplayed()
        composeTestRule.onNodeWithText(regPriceText).assertIsDisplayed()
        composeTestRule.onNodeWithText(deal.fulfillment).assertIsDisplayed()
        composeTestRule.onNodeWithText(prodDetailsText).assertIsDisplayed()
    }

    @Test
    fun addToCartDisplayedAndClickable() {
        val deal = Generators.dealItem(id = 22, regularPriceInCents = 5000, salePriceInCents = 4000)

        var addedToCart = false

        composeTestRule.setContent {
            TargetTheme {
                DealDetailsScreen(
                    dealItem = deal,
                    isLoading = false,
                    onAddToCart = { addedToCart = true },
                    onRetryDownload = {}
                )
            }
        }

        val text = composeTestRule.activity.getString(R.string.add_to_cart)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
        composeTestRule.onNodeWithText(text).performClick()

        Assert.assertTrue(addedToCart)
    }
}
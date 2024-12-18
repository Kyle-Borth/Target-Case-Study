package com.target.targetcasestudy.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.target.targetcasestudy.R
import com.target.targetcasestudy.UiTestActivity
import com.target.targetcasestudy.api.Price
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.ui.theme.TargetTheme
import org.junit.Rule
import org.junit.Test

private const val SALE_PRICE = "$123.45"
private const val REGULAR_PRICE = "$234.56"
private const val FULFILLMENT = "In Store"

class DealItemPriceTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<UiTestActivity>()

    private val dealItem = DealItem(
        id = 0,
        title = "",
        aisle = "",
        description = "",
        imageUrl = "",
        regularPrice = Price(amountInCents = 0, currencySymbol = "", displayString = REGULAR_PRICE),
        salePrice = Price(amountInCents = 0, currencySymbol = "", displayString = SALE_PRICE),
        fulfillment = FULFILLMENT,
        availability = ""
    )

    @Test
    fun salePriceIsDisplayed() {
        composeTestRule.setContent {
            TargetTheme {
                DealItemPrice(dealItem = dealItem)
            }
        }

        composeTestRule.onNodeWithText(SALE_PRICE).assertIsDisplayed()
    }

    @Test
    fun regularPriceIsDisplayed() {
        composeTestRule.setContent {
            TargetTheme {
                DealItemPrice(dealItem = dealItem)
            }
        }

        val text = composeTestRule.activity.getString(R.string.reg_price, REGULAR_PRICE)
        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun fulfillmentIsDisplayed() {
        composeTestRule.setContent {
            TargetTheme {
                DealItemPrice(dealItem = dealItem)
            }
        }

        composeTestRule.onNodeWithText(FULFILLMENT).assertIsDisplayed()
    }

}
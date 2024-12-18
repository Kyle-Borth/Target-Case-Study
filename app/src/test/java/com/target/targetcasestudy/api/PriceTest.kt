package com.target.targetcasestudy.api

import org.junit.Assert
import org.junit.Test

class PriceTest {

    @Test
    fun `toString is displayString`() {
        val expectedString = "DISPLAY_STRING"
        val price = Price(amountInCents = 22, currencySymbol = "CURRENCY_SYMBOL", displayString = expectedString)

        val actualString = price.toString()

        Assert.assertEquals(expectedString, actualString)
    }

}
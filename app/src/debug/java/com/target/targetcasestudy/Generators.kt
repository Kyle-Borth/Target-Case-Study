package com.target.targetcasestudy

import android.icu.text.DecimalFormat
import com.target.targetcasestudy.api.Price
import com.target.targetcasestudy.api.Product
import com.target.targetcasestudy.data.DealItem

object Generators {

    fun product(id: Int, regularPriceInCents: Int, salePriceInCents: Int?) = Product(
        id = id,
        title = "Product $id",
        aisle = "Aisle $id",
        description = "Description $id",
        imageUrl = "Image URL $id",
        regularPrice = price(regularPriceInCents),
        salePrice = salePriceInCents?.let { price(it) },
        fulfillment = "Fulfillment $id",
        availability = "Availability $id"
    )

    fun dealItem(id: Int, regularPriceInCents: Int, salePriceInCents: Int) = DealItem(
        id = id,
        title = "Deal Item $id",
        aisle = "Aisle $id",
        description = "Description $id",
        imageUrl = "Image URL $id",
        regularPrice = price(regularPriceInCents),
        salePrice = price(salePriceInCents),
        fulfillment = "Fulfillment $id",
        availability = "Availability $id"
    )

    private fun price(cents: Int, currencySymbol: String = "$") = Price(
        amountInCents = cents,
        currencySymbol = currencySymbol,
        displayString = "$currencySymbol$cents"
    )
    
}
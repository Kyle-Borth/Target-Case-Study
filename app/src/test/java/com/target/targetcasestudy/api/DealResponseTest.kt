package com.target.targetcasestudy.api

import com.target.targetcasestudy.Generators
import org.junit.Assert
import org.junit.Test

class DealResponseTest {

    @Test
    fun `Deals does not include products without a sale price`() {
        val response = DealResponse(
            products = listOf(
                Generators.product(id = 0, regularPriceInCents = 2000, salePriceInCents = 1000),
                Generators.product(id = 1, regularPriceInCents = 3000, salePriceInCents = null),
                Generators.product(id = 2, regularPriceInCents = 4000, salePriceInCents = 3000)
            )
        )

        Assert.assertEquals(2, response.deals.size)
        Assert.assertEquals(response.products[0].id, response.deals[0].id)
        Assert.assertEquals(response.products[2].id, response.deals[1].id)
    }

    @Test
    fun `Products convert to DealItems`() {
        val product = Generators.product(id = 22, regularPriceInCents = 2000, salePriceInCents = 1000)
        val response = DealResponse(products = listOf(product))

        val dealItem = response.deals.first()

        Assert.assertEquals(product.id, dealItem.id)
        Assert.assertEquals(product.title, dealItem.title)
        Assert.assertEquals(product.description, dealItem.description)
        Assert.assertEquals(product.imageUrl, dealItem.imageUrl)
        Assert.assertEquals(product.regularPrice, dealItem.regularPrice)
        Assert.assertEquals(product.salePrice, dealItem.salePrice)
        Assert.assertEquals(product.fulfillment, dealItem.fulfillment)
        Assert.assertEquals(product.availability, dealItem.availability)
    }

}

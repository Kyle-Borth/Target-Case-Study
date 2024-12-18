package com.target.targetcasestudy.data

import com.target.targetcasestudy.Generators
import org.junit.Assert
import org.junit.Test

class DealItemTest {

    @Test
    fun `Product with sale price is same as DealItem`() {
        val product = Generators.product(id = 22, regularPriceInCents = 5000, salePriceInCents = 4500)

        val dealItem = product.toDealItem()

        Assert.assertEquals(product.id, dealItem?.id)
        Assert.assertEquals(product.title, dealItem?.title)
        Assert.assertEquals(product.aisle, dealItem?.aisle)
        Assert.assertEquals(product.description, dealItem?.description)
        Assert.assertEquals(product.imageUrl, dealItem?.imageUrl)
        Assert.assertEquals(product.regularPrice, dealItem?.regularPrice)
        Assert.assertEquals(product.salePrice, dealItem?.salePrice)
        Assert.assertEquals(product.fulfillment, dealItem?.fulfillment)
        Assert.assertEquals(product.availability, dealItem?.availability)
    }

    @Test
    fun `Product without sale price is not a DealItem`() {
        val product = Generators.product(id = 23, regularPriceInCents = 10000, salePriceInCents = null)

        val dealItem = product.toDealItem()

        Assert.assertNull(dealItem)
    }

}
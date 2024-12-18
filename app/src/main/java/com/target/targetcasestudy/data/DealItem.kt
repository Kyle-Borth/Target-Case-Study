package com.target.targetcasestudy.data

import com.target.targetcasestudy.api.Price
import com.target.targetcasestudy.api.Product

data class DealItem(
    val id: Int,
    val title: String,
    val aisle: String,
    val description: String,
    val imageUrl: String,
    val regularPrice: Price,
    val salePrice: Price,
    val fulfillment: String,
    val availability: String
)

fun Product.toDealItem(): DealItem? = salePrice?.let { salePrice ->
    DealItem(
        id = id,
        title = title,
        aisle = aisle,
        description = description,
        imageUrl = imageUrl,
        regularPrice = regularPrice,
        salePrice = salePrice,
        fulfillment = fulfillment,
        availability = availability
    )
}
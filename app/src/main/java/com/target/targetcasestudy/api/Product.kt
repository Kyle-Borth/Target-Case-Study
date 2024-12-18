package com.target.targetcasestudy.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("aisle") val aisle: String,
    @SerialName("description") val description: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("regular_price") val regularPrice: Price,
    @SerialName("sale_price") val salePrice: Price? = null,
    @SerialName("fulfillment") val fulfillment: String,
    @SerialName("availability") val availability: String
)

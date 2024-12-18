package com.target.targetcasestudy.api

import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.toDealItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DealResponse(@SerialName("products") val products: List<Product>) {

    val deals: List<DealItem> get() = products.mapNotNull { it.toDealItem() }

}
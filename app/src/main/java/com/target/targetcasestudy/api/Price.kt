package com.target.targetcasestudy.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Price(
    @SerialName("amount_in_cents") val amountInCents: Int,
    @SerialName("currency_symbol") val currencySymbol: String,
    @SerialName("display_string") val displayString: String
) {
    override fun toString() = displayString
}
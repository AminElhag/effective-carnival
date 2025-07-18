package com.example.backend.mobileClient.features.contract.controller.models

import com.example.backend.mobileClient.features.membership.service.dto.PaymentPlanDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class CheckoutInitResponse(
    @SerialName("total_amount_without_tax") val totalAmountWithoutTax: String,
    @SerialName("total_tax") val totalTax: String,
    @SerialName("total_amount") val totalAmount: String,
    @SerialName("contract_id") val contractId: Long
)

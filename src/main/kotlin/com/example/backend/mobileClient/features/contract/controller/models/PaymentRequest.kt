package com.example.backend.mobileClient.features.contract.controller.models

import com.example.backend.mobileClient.features.contract.service.dto.PaymentDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaymentRequest(
    @SerialName("contract_id")
    val contractId: Long,
    @SerialName("card_number")
    val cardNumber: String,
    @SerialName("expiration_date")
    val expirationDate: String,
    @SerialName("card_cvc")
    val cardCVV: String
) {
    fun toDto() = PaymentDto(
        this.contractId,
        this.cardNumber,
        this.expirationDate,
        this.cardCVV
    )
}

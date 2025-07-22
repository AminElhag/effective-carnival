package com.example.backend.mobileClient.features.contract.service.dto

data class PaymentDto(
    val contractId: Long,
    val cardNumber: String,
    val expirationDate: String,
    val cardCVV: String
) {

}

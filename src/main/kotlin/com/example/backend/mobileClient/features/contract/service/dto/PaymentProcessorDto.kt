package com.example.backend.mobileClient.features.contract.service.dto

data class PaymentProcessorDto(
    val success: Boolean,
    val transactionId: String?,
    val message: String,
    val processorReference: String? = null,
    val errorCode: String? = null
)
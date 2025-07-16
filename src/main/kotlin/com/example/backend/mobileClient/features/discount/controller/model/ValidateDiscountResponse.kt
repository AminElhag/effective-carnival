package com.example.backend.mobileClient.features.discount.controller.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValidateDiscountResponse(
    @SerialName("is_valid")
    val isValid: Boolean,
    @SerialName("message")
    val message: String,
)

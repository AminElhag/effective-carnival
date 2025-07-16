package com.example.backend.mobileClient.features.discount.service.dto

import com.example.backend.mobileClient.features.discount.controller.model.ValidateDiscountResponse

data class ValidateDiscountDto(
    val isValid: Boolean,
    val message: String,
) {
    fun toResponse()= ValidateDiscountResponse(
        isValid = isValid,
        message = message,
    )
}

package com.example.backend.mobileClient.features.discount.service

import com.example.backend.mobileClient.features.discount.service.dto.ValidateDiscountDto

interface DiscountService {
    fun validate(code: String, paymentPlanId: Long): ValidateDiscountDto
}

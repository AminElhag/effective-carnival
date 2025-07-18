package com.example.backend.mobileClient.features.discount.service.Impl

import com.example.backend.mobileClient.common.DiscountType
import com.example.backend.mobileClient.discounts
import com.example.backend.mobileClient.features.discount.repository.entity.Discount
import com.example.backend.mobileClient.features.discount.service.DiscountService
import com.example.backend.mobileClient.features.discount.service.dto.ValidateDiscountDto
import com.example.backend.mobileClient.plans
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class DiscountServiceImpl : DiscountService {


    fun Discount.isActive(currentTime: LocalDate): Boolean {
        if (!active) return false
        if (startDate?.isAfter(currentTime) == true) return false
        if (endDate?.isBefore(currentTime) == true) return false
        return true
    }

    fun Discount.isValidForPlan(paymentPlanId: Long): Boolean {
        return allPaymentPlans || paymentPlans.any { it.id == paymentPlanId }
    }

    // Refactored validate function
    override fun validate(code: String, paymentPlanId: Long): ValidateDiscountDto {
        val currentTime = LocalDate.now()

        val discount = discounts.find { it.promoCode == code }
            ?: return ValidateDiscountDto(false, "Invalid promo code")

        return when {
            !discount.isActive(currentTime) -> ValidateDiscountDto(false, "Discount is not active or expired")
            !discount.isValidForPlan(paymentPlanId) -> ValidateDiscountDto(false, "Not valid for payment plan")
            else -> ValidateDiscountDto(true, "Valid discount")
        }
    }
}




package com.example.backend.mobileClient.features.discount.service.dto

import com.example.backend.mobileClient.common.DiscountType
import com.example.backend.mobileClient.features.discount.repository.entity.Discount
import com.example.backend.mobileClient.features.membership.repository.entity.PaymentPlan
import java.math.BigDecimal
import java.time.LocalDateTime

data class DiscountDto(

    val id: Long?,

    val name: String?,

    val active: Boolean?,

    val membershipFee:Boolean?,

    val membershipFeeDiscount: BigDecimal?,

    val membershipFeeDiscountType: DiscountType?,

    val hasAdminFee:Boolean = false,

    val adminFee: BigDecimal?,

    val adminFeeDiscountType: DiscountType,

    val hasJoinFee:Boolean = false,

    val joinFee: BigDecimal = BigDecimal.ZERO,

    val joinFeeDiscountType: DiscountType? = null,

    val paymentPlans:Set<PaymentPlan>?,

    val allPaymentPlans: Boolean = false,

    val hasPromoCode:Boolean = false,

    val promoCode: String? = null,

    val onePerUser: Boolean = true,

    val incompatibleDiscounts: Set<Discount> = emptySet(),

    val startDate: LocalDateTime? = null,

    val endDate: LocalDateTime? = null,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
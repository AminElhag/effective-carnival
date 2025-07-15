package com.example.backend.mobileClient.features.membership.service.dto

import com.example.backend.mobileClient.common.PaymentMethod
import com.example.backend.mobileClient.common.PeriodType
import com.example.backend.mobileClient.common.PlanType
import com.example.backend.mobileClient.features.membership.controller.models.MembershipPlan
import com.example.backend.mobileClient.features.membership.repository.entity.Agreement
import java.math.BigDecimal
import java.time.LocalDateTime

data class PaymentPlanDto(
    val id: Long?,
    val name: String?,
    val fiscalName: String?,
    val description: String?,
    val minimumAge: Int?,
    val maxAge: Int?,
    val active: Boolean?,
    val planType: PlanType?,
    val membershipFee: BigDecimal?,
    val membershipFeeTax: BigDecimal = BigDecimal.ZERO,
    val administrationFee: BigDecimal = BigDecimal.ZERO,
    val administrationFeeTax: BigDecimal = BigDecimal.ZERO,
    val joiningFee: BigDecimal = BigDecimal.ZERO,
    val joiningFeeTax: BigDecimal = BigDecimal.ZERO,
    val paymentMethods: List<PaymentMethod>?,
    val paymentInterval: Int?,
    val paymentIntervalType: PeriodType?,
    val commitmentPeriod: Int?,
    val commitmentPeriodType: PeriodType?,
    val minimumCancellationPeriod: Int?,
    val minimumCancellationPeriodType: PeriodType?,
    val maximumCancellationPeriod: Int?,
    val maximumCancellationPeriodType: PeriodType?,
    val firstPaymentOnSignUpDate: Boolean?,
    val automaticallyEndContractAfterCommitmentPeriodReached: Boolean?,
    val isLimitNumberVisitsInThePeriod: Boolean?,
    val limitNumberVisitsInThePeriod: Int?,
    val canBeRenewed: Boolean?,
    val automaticRenew: Boolean?,
    val agreements: List<Agreement>?,
    val startDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?,
) {
    /*fun toPaymentPlan(): PaymentPlan {
        return PaymentPlan()
    }*/
    fun toMembershipResponse() = MembershipPlan(
        id = id!!,
        name = name!!,
        price = membershipFee?.add(administrationFee)?.add(joiningFee)?.toDouble() ?: 0.0,
        description = description!!,
        commitmentPeriod = "$commitmentPeriod $commitmentPeriodType",
        paymentInterval = "$paymentInterval $paymentIntervalType",
        minimumCancellationPeriod = if (minimumCancellationPeriod != null)"$minimumCancellationPeriod $minimumCancellationPeriodType" else null,
        maxCancellationPeriod = if (maximumCancellationPeriod != null)"$maximumCancellationPeriod $maximumCancellationPeriodType" else null,
        limitNumberOfVisits = limitNumberVisitsInThePeriod,
        canBeRenew = canBeRenewed ?: false,
    )
}

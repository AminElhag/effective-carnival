package com.example.backend.mobileClient.features.discount.service.Impl

import com.example.backend.mobileClient.common.DiscountType
import com.example.backend.mobileClient.common.PaymentMethod
import com.example.backend.mobileClient.common.PeriodType
import com.example.backend.mobileClient.common.PlanType
import com.example.backend.mobileClient.features.discount.repository.entity.Discount
import com.example.backend.mobileClient.features.discount.service.DiscountService
import com.example.backend.mobileClient.features.discount.service.dto.DiscountDto
import com.example.backend.mobileClient.features.discount.service.dto.ValidateDiscountDto
import com.example.backend.mobileClient.features.membership.repository.entity.Agreement
import com.example.backend.mobileClient.features.membership.repository.entity.PaymentPlan
import com.example.backend.mobileClient.features.membership.service.imp.AgreementTemplates.BASIC_MEMBERSHIP_HTML
import com.example.backend.mobileClient.features.membership.service.imp.AgreementTemplates.PREMIUM_MEMBERSHIP_HTML
import com.example.backend.mobileClient.features.membership.service.imp.AgreementTemplates.STUDENT_MEMBERSHIP_HTML
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class DiscountServiceImpl : DiscountService {

    val plans = listOf(
        PaymentPlan(
            id = 1,
            name = "Basic Membership",
            fiscalName = "BASIC_MEMBERSHIP_2024",
            description = "Our affordable basic membership plan with essential gym access",
            minimumAge = 16,
            maxAge = 65,
            active = true,
            planType = PlanType.CONTRACT,
            membershipFee = BigDecimal("29.99"),
            membershipFeeTax = BigDecimal("2.40"),
            administrationFee = BigDecimal("5.00"),
            administrationFeeTax = BigDecimal("0.40"),
            joiningFee = BigDecimal("25.00"),
            joiningFeeTax = BigDecimal("2.00"),
            paymentMethods = listOf(PaymentMethod.CREDIT_CARD, PaymentMethod.FRONT_DESK),
            paymentInterval = 1,
            paymentIntervalType = PeriodType.MONTH,
            commitmentPeriod = 12,
            commitmentPeriodType = PeriodType.MONTH,
            minimumCancellationPeriod = 30,
            minimumCancellationPeriodType = PeriodType.DAY,
            firstPaymentOnSignUpDate = true,
            canBeRenewed = true,
            automaticRenew = false,
            agreements = listOf(
                Agreement(
                    id = 1,
                    title = "BASIC_MEMBERSHIP_2024",
                    body = BASIC_MEMBERSHIP_HTML
                )
            ),

            ),
        PaymentPlan(
            id = 2,
            name = "Premium Membership",
            fiscalName = "PREMIUM_MEMBERSHIP_2024",
            description = "Our premium membership with exclusive benefits and 24/7 access",
            minimumAge = 18,
            maxAge = null,
            active = true,
            planType = PlanType.CONTRACT,
            membershipFee = BigDecimal("59.99"),
            membershipFeeTax = BigDecimal("4.80"),
            administrationFee = BigDecimal("10.00"),
            administrationFeeTax = BigDecimal("0.80"),
            joiningFee = BigDecimal("50.00"),
            joiningFeeTax = BigDecimal("4.00"),
            paymentMethods = listOf(PaymentMethod.CREDIT_CARD, PaymentMethod.DIRECT_DEBIT, PaymentMethod.DIRECT_DEBIT),
            paymentInterval = 1,
            paymentIntervalType = PeriodType.MONTH,
            commitmentPeriod = 12,
            commitmentPeriodType = PeriodType.MONTH,
            minimumCancellationPeriod = 30,
            minimumCancellationPeriodType = PeriodType.DAY,
            firstPaymentOnSignUpDate = true,
            canBeRenewed = true,
            automaticRenew = true,
            agreements = listOf(
                Agreement(
                    id = 2,
                    title = "PREMIUM_MEMBERSHIP_2024",
                    body = PREMIUM_MEMBERSHIP_HTML
                )
            )
        ),
        PaymentPlan(
            id = 3,
            name = "Student Membership",
            fiscalName = "STUDENT_MEMBERSHIP_2024",
            description = "Discounted membership for students with valid student ID",
            minimumAge = 16,
            maxAge = 25,
            active = true,
            planType = PlanType.CONTRACT,
            membershipFee = BigDecimal("19.99"),
            membershipFeeTax = BigDecimal("1.60"),
            administrationFee = BigDecimal("3.00"),
            administrationFeeTax = BigDecimal("0.24"),
            joiningFee = BigDecimal("15.00"),
            joiningFeeTax = BigDecimal("1.20"),
            paymentMethods = listOf(PaymentMethod.CREDIT_CARD, PaymentMethod.COMPANY_INVOICE),
            paymentInterval = 1,
            paymentIntervalType = PeriodType.MONTH,
            commitmentPeriod = 6,
            commitmentPeriodType = PeriodType.MONTH,
            minimumCancellationPeriod = 14,
            minimumCancellationPeriodType = PeriodType.DAY,
            firstPaymentOnSignUpDate = true,
            canBeRenewed = true,
            automaticRenew = false,
            agreements = listOf(
                Agreement(
                    id = 3,
                    title = "STUDENT_MEMBERSHIP_2024",
                    body = STUDENT_MEMBERSHIP_HTML
                )
            )
        )
    )

    val discounts = listOf(
        // 1. Membership Fee Discount (Percentage)
        Discount(
            name = "Gold Member Discount",
            active = true,
            membershipFee = true,
            membershipFeeDiscount = BigDecimal("15.00"),
            membershipFeeDiscountType = DiscountType.PERCENTAGE,
            allPaymentPlans = true
        ),

        // 2. Fixed Admin Fee Discount
        Discount(
            name = "Admin Fee Waiver",
            active = true,
            hasAdminFee = true,
            adminFee = BigDecimal("0.00"),
            adminFeeDiscountType = DiscountType.FIXED_PRICE,
            paymentPlans = setOf(plans[1]),
            onePerUser = false
        ),

        // 3. Join Fee Discount with Promo Code
        Discount(
            name = "Summer Welcome Offer",
            active = true,
            hasJoinFee = true,
            joinFee = BigDecimal("25.00"),
            joinFeeDiscountType = DiscountType.AMOUNT,
            hasPromoCode = true,
            promoCode = "SUN_2023",
            startDate = LocalDate.of(2025, 1, 1),
            endDate = LocalDate.of(2025, 8, 31, )
        ),

        // 4. Combo Discount (Membership + Admin)
        Discount(
            name = "Platinum Package",
            active = true,
            membershipFee = true,
            membershipFeeDiscount = BigDecimal("20.00"),
            membershipFeeDiscountType = DiscountType.PERCENTAGE,
            hasAdminFee = true,
            adminFee = BigDecimal("10.00"),
            adminFeeDiscountType = DiscountType.FIXED_PRICE,
            allPaymentPlans = true
        ),

        // 5. Short-Term Promo (Fixed Amount)
        Discount(
            name = "Black Friday Special",
            active = true,
            membershipFee = true,
            membershipFeeDiscount = BigDecimal("50.00"),
            membershipFeeDiscountType = DiscountType.AMOUNT,
            hasPromoCode = true,
            promoCode = "BLKFRIDAY",
            startDate = LocalDate.of(2023, 11, 24),
            endDate = LocalDate.of(2023, 11, 27),
            onePerUser = true
        ),

        // 6. Inactive Discount
        Discount(
            name = "Legacy Discount",
            active = false,
            membershipFee = true,
            membershipFeeDiscount = BigDecimal("10.00"),
            membershipFeeDiscountType = DiscountType.PERCENTAGE
        ),

        // 7. Payment Plan Specific Discount
        Discount(
            name = "Quarterly Plan Bonus",
            active = true,
            membershipFee = true,
            membershipFeeDiscount = BigDecimal("5.00"),
            membershipFeeDiscountType = DiscountType.PERCENTAGE,
            paymentPlans = setOf(plans[2]),
        ),

        // 8. Join Fee Percentage Discount
        Discount(
            name = "Student Welcome Pack",
            active = true,
            hasJoinFee = true,
            joinFee = BigDecimal("0.00"),
            joinFeeDiscountType = DiscountType.FIXED_PRICE,
            hasPromoCode = true,
            promoCode = "STUDENT23",
            onePerUser = true
        ),

        // 9. Full Package Discount
        Discount(
            name = "All-Inclusive Offer",
            active = true,
            membershipFee = true,
            membershipFeeDiscount = BigDecimal("30.00"),
            membershipFeeDiscountType = DiscountType.PERCENTAGE,
            hasAdminFee = true,
            adminFee = BigDecimal("0.00"),
            adminFeeDiscountType = DiscountType.FIXED_PRICE,
            hasJoinFee = true,
            joinFee = BigDecimal("0.00"),
            joinFeeDiscountType = DiscountType.FIXED_PRICE,
            allPaymentPlans = true,
        ),

        // 10. No Fee Discount
        Discount(
            name = "Corporate Partnership",
            active = true,
            membershipFee = true,
            membershipFeeDiscount = BigDecimal("100.00"),
            membershipFeeDiscountType = DiscountType.PERCENTAGE,
            hasAdminFee = true,
            adminFee = BigDecimal("0.00"),
            adminFeeDiscountType = DiscountType.FIXED_PRICE,
            hasPromoCode = true,
            promoCode = "CORP1234",
            onePerUser = false
        )
    )

    /*override fun validate(code: String, paymentPlanId: Long): ValidateDiscountDto {
        val currentTime = LocalDateTime.now()

        val discount = discounts.find { it.promoCode == code }
            ?: return ValidateDiscountDto(false, "Invalid promo code")

        if (!discount.active) {
            return ValidateDiscountDto(false, "Discount is inactive")
        }

        discount.startDate?.let {
            if (it.isAfter(currentTime)) {
                return ValidateDiscountDto(false, "Discount hasn't started yet")
            }
        }

        discount.endDate?.let {
            if (it.isBefore(currentTime)) {
                return ValidateDiscountDto(false, "Discount has expired")
            }
        }

        return when {
            discount.allPaymentPlans -> ValidateDiscountDto(true, "Valid for all payment plans")
            discount.paymentPlans.any { it.id == paymentPlanId } -> ValidateDiscountDto(true, "Valid for selected plan")
            else -> ValidateDiscountDto(false, "Not valid for selected payment plan")
        }
    }*/

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




package com.example.backend.mobileClient

import com.example.backend.mobileClient.AgreementTemplates.BASIC_MEMBERSHIP_HTML
import com.example.backend.mobileClient.AgreementTemplates.PREMIUM_MEMBERSHIP_HTML
import com.example.backend.mobileClient.AgreementTemplates.STUDENT_MEMBERSHIP_HTML
import com.example.backend.mobileClient.common.ContractType
import com.example.backend.mobileClient.common.DiscountType
import com.example.backend.mobileClient.common.EmploymentType
import com.example.backend.mobileClient.common.Gender
import com.example.backend.mobileClient.common.PaymentMethod
import com.example.backend.mobileClient.common.PeriodType
import com.example.backend.mobileClient.common.PlanType
import com.example.backend.mobileClient.features.contract.repository.entity.Contract
import com.example.backend.mobileClient.features.discount.repository.entity.Discount
import com.example.backend.mobileClient.features.employee.repository.entity.Employee
import com.example.backend.mobileClient.features.members.repository.entity.Member
import com.example.backend.mobileClient.features.membership.repository.entity.Agreement
import com.example.backend.mobileClient.features.membership.repository.entity.PaymentPlan
import java.math.BigDecimal
import java.time.LocalDate

object AgreementTemplates {
    val BASIC_MEMBERSHIP_HTML = """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Basic Membership Agreement</title>
            <style>
                body { font-family: Arial, sans-serif; margin: 20px; }
                .header { color: #2c3e50; border-bottom: 2px solid #3498db; padding-bottom: 10px; }
                .section { margin: 20px 0; }
                .highlight { background-color: #f8f9fa; padding: 10px; border-left: 4px solid #3498db; }
            </style>
        </head>
        <body>
            <div class="header">
                <h1>Basic Membership Agreement</h1>
            </div>
            <div class="section">
                <h2>Terms and Conditions</h2>
                <p>This agreement governs your basic membership with our fitness center.</p>
                <div class="highlight">
                    <p><strong>Monthly Fee:</strong> $29.99</p>
                    <p><strong>Commitment Period:</strong> 12 months</p>
                    <p><strong>Cancellation Notice:</strong> 30 days</p>
                </div>
            </div>
            <div class="section">
                <h2>Member Benefits</h2>
                <ul>
                    <li>Access to gym equipment during regular hours</li>
                    <li>One guest pass per month</li>
                    <li>Access to basic fitness classes</li>
                </ul>
            </div>
        </body>
        </html>
    """.trimIndent()

    val PREMIUM_MEMBERSHIP_HTML = """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Premium Membership Agreement</title>
            <style>
                body { font-family: Arial, sans-serif; margin: 20px; }
                .header { color: #8e44ad; border-bottom: 2px solid #9b59b6; padding-bottom: 10px; }
                .section { margin: 20px 0; }
                .highlight { background-color: #f4f2f7; padding: 10px; border-left: 4px solid #9b59b6; }
                .premium-badge { background: linear-gradient(45deg, #8e44ad, #9b59b6); color: white; padding: 5px 10px; border-radius: 15px; display: inline-block; }
            </style>
        </head>
        <body>
            <div class="header">
                <h1>Premium Membership Agreement <span class="premium-badge">PREMIUM</span></h1>
            </div>
            <div class="section">
                <h2>Premium Terms and Conditions</h2>
                <p>This agreement governs your premium membership with exclusive benefits.</p>
                <div class="highlight">
                    <p><strong>Monthly Fee:</strong> $59.99</p>
                    <p><strong>Commitment Period:</strong> 12 months</p>
                    <p><strong>Cancellation Notice:</strong> 30 days</p>
                </div>
            </div>
            <div class="section">
                <h2>Premium Benefits</h2>
                <ul>
                    <li>24/7 gym access</li>
                    <li>Unlimited guest passes</li>
                    <li>All fitness classes included</li>
                    <li>Personal trainer consultation</li>
                    <li>Access to sauna and spa facilities</li>
                </ul>
            </div>
        </body>
        </html>
    """.trimIndent()

    val STUDENT_MEMBERSHIP_HTML = """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Student Membership Agreement</title>
            <style>
                body { font-family: Arial, sans-serif; margin: 20px; }
                .header { color: #27ae60; border-bottom: 2px solid #2ecc71; padding-bottom: 10px; }
                .section { margin: 20px 0; }
                .highlight { background-color: #f0fff4; padding: 10px; border-left: 4px solid #2ecc71; }
                .student-badge { background: linear-gradient(45deg, #27ae60, #2ecc71); color: white; padding: 5px 10px; border-radius: 15px; display: inline-block; }
            </style>
        </head>
        <body>
            <div class="header">
                <h1>Student Membership Agreement <span class="student-badge">STUDENT</span></h1>
            </div>
            <div class="section">
                <h2>Student Terms and Conditions</h2>
                <p>This agreement governs your discounted student membership.</p>
                <div class="highlight">
                    <p><strong>Monthly Fee:</strong> $19.99</p>
                    <p><strong>Commitment Period:</strong> 6 months</p>
                    <p><strong>Cancellation Notice:</strong> 14 days</p>
                    <p><strong>Verification Required:</strong> Valid student ID</p>
                </div>
            </div>
            <div class="section">
                <h2>Student Benefits</h2>
                <ul>
                    <li>Access during off-peak hours (6 AM - 4 PM)</li>
                    <li>Basic fitness classes</li>
                    <li>Study area access</li>
                    <li>Discounted personal training sessions</li>
                </ul>
            </div>
        </body>
        </html>
    """.trimIndent()
}

val plans = listOf(
    PaymentPlan(
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
                title = "Terms of Service",
                body = BASIC_MEMBERSHIP_HTML,
                required = true,
            ),
            Agreement(
                id = 2,
                title = "Privacy Policy",
                body = PREMIUM_MEMBERSHIP_HTML,
                required = true
            )
        ),

        ),
    PaymentPlan(
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
                id = 1,
                title = "Terms of Service",
                body = BASIC_MEMBERSHIP_HTML,
                required = true,
            ),
            Agreement(
                id = 2,
                title = "Privacy Policy",
                body = PREMIUM_MEMBERSHIP_HTML,
                required = true
            )
        )
    ),
    PaymentPlan(
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
                id = 1,
                title = "Terms of Service",
                body = BASIC_MEMBERSHIP_HTML,
                required = true,
            ),
            Agreement(
                id = 2,
                title = "Privacy Policy",
                body = PREMIUM_MEMBERSHIP_HTML,
                required = true
            ),
            Agreement(
                id = 3,
                title = "Student Policy",
                body = STUDENT_MEMBERSHIP_HTML,
                required = true
            )
        )
    )
)

val discounts = listOf(
    // 1. Membership Fee Discount (Percentage)
    Discount(
        name = "Gold Member Discount",
        active = true,
        hasMembershipFeeDiscount = true,
        membershipFeeDiscount = BigDecimal("15.00"),
        membershipFeeDiscountType = DiscountType.PERCENTAGE,
        allPaymentPlans = true
    ),

    // 2. Fixed Admin Fee Discount
    Discount(
        name = "Admin Fee Waiver",
        active = true,
        hasAdminFeeDiscount = true,
        adminFeeDiscount = BigDecimal("0.00"),
        adminFeeDiscountType = DiscountType.FIXED_PRICE,
        paymentPlans = setOf(plans[1]),
        onePerUser = false
    ),

    // 3. Join Fee Discount with Promo Code
    Discount(
        name = "Summer Welcome Offer",
        active = true,
        hasJoinFeeDiscount = true,
        joinFeeDiscount = BigDecimal("25.00"),
        joinFeeDiscountType = DiscountType.AMOUNT,
        hasPromoCode = true,
        promoCode = "SUN_2023",
        startDate = LocalDate.of(2025, 1, 1),
        endDate = LocalDate.of(2025, 8, 31)
    ),

    // 4. Combo Discount (Membership + Admin)
    Discount(
        name = "Platinum Package",
        active = true,
        hasMembershipFeeDiscount = true,
        membershipFeeDiscount = BigDecimal("20.00"),
        membershipFeeDiscountType = DiscountType.PERCENTAGE,
        hasAdminFeeDiscount = true,
        adminFeeDiscount = BigDecimal("10.00"),
        adminFeeDiscountType = DiscountType.FIXED_PRICE,
        allPaymentPlans = true
    ),

    // 5. Short-Term Promo (Fixed Amount)
    Discount(
        name = "Black Friday Special",
        active = true,
        hasMembershipFeeDiscount = true,
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
        hasMembershipFeeDiscount = true,
        membershipFeeDiscount = BigDecimal("10.00"),
        membershipFeeDiscountType = DiscountType.PERCENTAGE
    ),

    // 7. Payment Plan Specific Discount
    Discount(
        name = "Quarterly Plan Bonus",
        active = true,
        hasMembershipFeeDiscount = true,
        membershipFeeDiscount = BigDecimal("5.00"),
        membershipFeeDiscountType = DiscountType.PERCENTAGE,
        paymentPlans = setOf(plans[2]),
    ),

    // 8. Join Fee Percentage Discount
    Discount(
        name = "Student Welcome Pack",
        active = true,
        hasJoinFeeDiscount = true,
        joinFeeDiscount = BigDecimal("0.00"),
        joinFeeDiscountType = DiscountType.FIXED_PRICE,
        hasPromoCode = true,
        promoCode = "STUDENT23",
        onePerUser = true
    ),

    // 9. Full Package Discount
    Discount(
        name = "All-Inclusive Offer",
        active = true,
        hasMembershipFeeDiscount = true,
        membershipFeeDiscount = BigDecimal("30.00"),
        membershipFeeDiscountType = DiscountType.PERCENTAGE,
        hasAdminFeeDiscount = true,
        adminFeeDiscount = BigDecimal("0.00"),
        adminFeeDiscountType = DiscountType.FIXED_PRICE,
        hasJoinFeeDiscount = true,
        joinFeeDiscount = BigDecimal("0.00"),
        joinFeeDiscountType = DiscountType.FIXED_PRICE,
        allPaymentPlans = true,
    ),

    // 10. No Fee Discount
    Discount(
        name = "Corporate Partnership",
        active = true,
        hasMembershipFeeDiscount = true,
        membershipFeeDiscount = BigDecimal("100.00"),
        membershipFeeDiscountType = DiscountType.PERCENTAGE,
        hasAdminFeeDiscount = true,
        adminFeeDiscount = BigDecimal("0.00"),
        adminFeeDiscountType = DiscountType.FIXED_PRICE,
        hasPromoCode = true,
        promoCode = "CORP1234",
        onePerUser = false
    )
)

val contract = Contract(
    id = 1,
    member = Member(
        id = 1,
        publicId = "1001001233",
        firstName = "Amin",
        middleName = "Galal",
        lastName = "Elhag",
        idNumber = "12002010020",
        dataOfBirth = LocalDate.now(),
        genderId = 1,
        phoneNumber = "010101011",
        email = "email@email.com",
        password = "password",
        emergencyContact = "1234567890",
        hearAboutUsId = 1,
        occupation = "Test",
        medicalConditionsIds = emptyList(),
        isDeleted = false,
        isValidation = true,
    ),
    consultant = Employee(
        id = 2,
        firstName = "Amin",
        lastName = "Elhag",
        birthdate = LocalDate.now(),
        gender = Gender.MALE,
        email = "email@email.com",
        mobilePhone = "010101011",
        homePhone = "010101011",
        street = "Street",
        city = "City",
        state = "State",
        systemRole = "System",
        availableInNewClubs = false,
        employmentDate = LocalDate.now(),
        employmentType = EmploymentType.FULL_TIME,
        postCode = "TODO()",
        position = "T",
        department = "TODO()",
        hasLoginAccount = true,
        username = "TODO()",
        password = "TODO()",
        profilePicturePath = "TODO()",
        autoDeactivate = false,
        employeeIdentifier = "TODO()",
        marketingNewsletters = false,
        active = true,
    ),
    plan = plans[1],
    type = ContractType.MAIN,
    endDate = LocalDate.now(),
    commitmentEndDate = LocalDate.now(),
    paymentMethod = PaymentMethod.DIRECT_DEBIT,
    totalAmount = BigDecimal("100.00"),
    totalAmountWithoutTax = BigDecimal("90.00"),
    totalTax = BigDecimal("10.00"),
    totalPaid = BigDecimal("100.00"),
    remainingAmount = BigDecimal.ZERO,
)
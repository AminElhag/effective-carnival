package com.example.backend.mobileClient.features.contract.service.impl

import com.example.backend.mobileClient.common.*
import com.example.backend.mobileClient.discounts
import com.example.backend.mobileClient.features.contract.repository.Contract
import com.example.backend.mobileClient.features.contract.service.ContractService
import com.example.backend.mobileClient.features.contract.service.dto.CheckoutInitDto
import com.example.backend.mobileClient.features.contract.service.dto.ContractDto
import com.example.backend.mobileClient.features.discount.repository.entity.Discount
import com.example.backend.mobileClient.features.employee.repository.entity.Employee
import com.example.backend.mobileClient.features.members.repository.entity.Member
import com.example.backend.mobileClient.plans
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate

@Service
class ContractServiceImpl : ContractService {


    override fun checkoutInit(toDto: CheckoutInitDto): ContractDto {
        val paymentPlan = plans.firstOrNull { it.id == toDto.membershipId }
        val discount = toDto.promoCode?.takeIf(String::isNotBlank)?.let { code ->
            discounts.firstOrNull { it.promoCode == code }
        }

        val initialFees = Triple(
            paymentPlan?.membershipFee ?: BigDecimal.ZERO,
            paymentPlan?.joiningFee ?: BigDecimal.ZERO,
            paymentPlan?.administrationFee ?: BigDecimal.ZERO
        )

        val (membershipFee, joinFee, administrationFee) = applyDiscounts(
            fees = initialFees,
            discount = discount
        )

        val totalAmount = membershipFee + joinFee + administrationFee

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
            plan = paymentPlan!!,
            type = ContractType.MAIN,
            endDate = LocalDate.now(),
            commitmentEndDate = LocalDate.now(),
            paymentMethod = PaymentMethod.DIRECT_DEBIT,
            totalAmount = totalAmount,
            totalAmountWithoutTax = totalAmount,
            totalTax = totalAmount,
            totalPaid = totalAmount,
            remainingAmount = BigDecimal.ZERO,
        )

        return contract.toDto()
    }

    private fun applyDiscounts(
        fees: Triple<BigDecimal, BigDecimal, BigDecimal>,
        discount: Discount?
    ): Triple<BigDecimal, BigDecimal, BigDecimal> {
        if (discount == null) return fees

        return Triple(
            applyFeeDiscount(
                original = fees.first,
                hasDiscount = discount.hasMembershipFeeDiscount,
                discountType = discount.membershipFeeDiscountType,
                discountValue = discount.membershipFeeDiscount
            ),
            applyFeeDiscount(
                original = fees.second,
                hasDiscount = discount.hasJoinFeeDiscount,
                discountType = discount.joinFeeDiscountType,
                discountValue = discount.joinFeeDiscount
            ),
            applyFeeDiscount(
                original = fees.third,
                hasDiscount = discount.hasAdminFeeDiscount,
                discountType = discount.adminFeeDiscountType,
                discountValue = discount.adminFeeDiscount
            )
        )
    }

    private fun applyFeeDiscount(
        original: BigDecimal,
        hasDiscount: Boolean,
        discountType: DiscountType?,
        discountValue: BigDecimal
    ): BigDecimal {
        if (!hasDiscount) return original

        return when (discountType) {
            DiscountType.PERCENTAGE -> {
                val discountAmount = original.multiply(discountValue)
                    .divide(100.toBigDecimal(), 2, RoundingMode.HALF_UP)
                BigDecimal.ZERO.max(original - discountAmount)
            }

            DiscountType.FIXED_PRICE -> BigDecimal.ZERO.max(original - discountValue)
            DiscountType.AMOUNT -> BigDecimal.ZERO.max(original - discountValue)
            null -> original
        }
    }
}
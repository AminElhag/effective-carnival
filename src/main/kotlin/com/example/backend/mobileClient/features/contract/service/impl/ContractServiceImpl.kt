package com.example.backend.mobileClient.features.contract.service.impl

import com.example.backend.mobileClient.common.*
import com.example.backend.mobileClient.contract
import com.example.backend.mobileClient.discounts
import com.example.backend.mobileClient.features.contract.controller.models.PaymentResponse
import com.example.backend.mobileClient.features.contract.repository.ContractRepository
import com.example.backend.mobileClient.features.contract.repository.entity.Contract
import com.example.backend.mobileClient.features.contract.repository.entity.Payment
import com.example.backend.mobileClient.features.contract.service.ContractService
import com.example.backend.mobileClient.features.contract.service.dto.CheckoutInitDto
import com.example.backend.mobileClient.features.contract.service.dto.ContractDto
import com.example.backend.mobileClient.features.contract.service.dto.PaymentDto
import com.example.backend.mobileClient.features.discount.repository.entity.Discount
import com.example.backend.mobileClient.features.employee.repository.entity.Employee
import com.example.backend.mobileClient.features.members.repository.MemberRepository
import com.example.backend.mobileClient.features.members.repository.entity.Member
import com.example.backend.mobileClient.plans
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class ContractServiceImpl : ContractService {

    @Autowired
    lateinit var paymentValidationService: PaymentValidationService

    @Autowired
    lateinit var paymentProcessorService: PaymentProcessorService

    @Autowired
    lateinit var contractRepository: ContractRepository


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

        val authentication = SecurityContextHolder.getContext().authentication
        val principal = authentication.principal as Member


        val contract = Contract(
            id = 1,
            member = principal,
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
            totalPaid = BigDecimal.ZERO,
            remainingAmount = totalAmount,
        )

        val save = contractRepository.save(contract)

        return save.toDto()
    }

    override fun processPayment(paymentDto: PaymentDto): PaymentResponse? {
        val validationErrors = paymentValidationService.validatePaymentRequest(paymentDto)

        if (validationErrors.isNotEmpty()) {
            return PaymentResponse(
                success = false,
                transactionId = null,
                message = "Validation failed: ${validationErrors.joinToString(", ")}",
                id = 1L,
            )
        }

        val transactionId = generateTransactionId()
        val paymentProvider = paymentValidationService.getPaymentProvider(paymentDto.cardNumber)
        val maskedCardNumber = maskCardNumber(paymentDto.cardNumber)

        val payment = Payment(
            transactionId = transactionId,
            maskedCardNumber = maskedCardNumber,
            paymentProvider = paymentProvider,
            amount = BigDecimal(100.00),
            currency = "SDG",
            status = PaymentStatus.PROCESSING,
            contract = contract,
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
        )

        try {
            // Process payment with external processor
            val processorResponse = paymentProcessorService.processPayment(paymentDto)

            // Update payment status based on processor response
            val updatedPayment = payment.copy(
                status = if (processorResponse.success) PaymentStatus.COMPLETED else PaymentStatus.FAILED,
                processorResponse = processorResponse.message,
                updatedAt = LocalDateTime.now()
            )

            /*paymentRepository.save(updatedPayment)*/

            return if (processorResponse.success) {
                PaymentResponse(
                    success = true,
                    transactionId = transactionId,
                    message = "Payment processed successfully",
                    id = 1L,
                )
            } else {
                PaymentResponse(
                    success = false,
                    transactionId = transactionId,
                    message = processorResponse.message,
                    id = 1L,
                )
            }
        }catch (e: Exception) {

            val failedPayment = payment.copy(
                status = PaymentStatus.FAILED,
                processorResponse = "Internal error: ${e.message}",
                updatedAt = LocalDateTime.now()
            )

            /*paymentRepository.save(failedPayment)*/

            return PaymentResponse(
                success = false,
                transactionId = transactionId,
                message = "Payment processing failed",
                id = 1L,
            )
        }
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

    private fun generateTransactionId(): String {
        return "PAY_${System.currentTimeMillis()}_${Random.nextInt(100000, 999999)}"
    }

    private fun maskCardNumber(cardNumber: String): String {
        val digits = cardNumber.replace("\\s".toRegex(), "")
        return if (digits.length >= 4) {
            "**** **** **** ${digits.takeLast(4)}"
        } else {
            "****"
        }
    }
}
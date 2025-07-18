package com.example.backend.mobileClient.features.contract.service.dto

import com.example.backend.mobileClient.common.ContractStatus
import com.example.backend.mobileClient.common.ContractType
import com.example.backend.mobileClient.common.PaymentMethod
import com.example.backend.mobileClient.features.contract.controller.models.CheckoutInitResponse
import com.example.backend.mobileClient.features.employee.repository.entity.Employee
import com.example.backend.mobileClient.features.members.repository.entity.Member
import com.example.backend.mobileClient.features.membership.repository.entity.PaymentPlan
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class ContractDto(

    val id: Long = 0,

    val member: Member? = null,

    val consultant: Employee? = null,

    val plan: PaymentPlan? = null,

    val type: ContractType = ContractType.MAIN,

    val signUpDate: LocalDate = LocalDate.now(),

    val startDate: LocalDate = LocalDate.now(),

    val endDate: LocalDate? = null,

    val commitmentEndDate: LocalDate? = null,

    val paymentMethod: PaymentMethod? = null,

    val totalAmount: BigDecimal? = null,

    val totalTax: BigDecimal? = null,

    val totalAmountWithoutTax: BigDecimal? = null,

    val totalPaid: BigDecimal? = null,

    val remainingAmount: BigDecimal? = null,

    val status: ContractStatus = ContractStatus.ACTIVE,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    val updatedAt: LocalDateTime = LocalDateTime.now(),

    ) {
    fun toCheckoutInit() = CheckoutInitResponse(
        contractId = id,
        totalAmount = totalAmount.toString(),
        totalTax = totalTax.toString(),
        totalAmountWithoutTax = totalAmountWithoutTax.toString(),
    )

}
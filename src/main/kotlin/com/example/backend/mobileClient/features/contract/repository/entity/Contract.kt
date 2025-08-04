package com.example.backend.mobileClient.features.contract.repository.entity

import com.example.backend.mobileClient.common.ContractStatus
import com.example.backend.mobileClient.common.ContractType
import com.example.backend.mobileClient.common.PaymentMethod
import com.example.backend.mobileClient.features.contract.service.dto.ContractDto
import com.example.backend.mobileClient.features.employee.repository.entity.Employee
import com.example.backend.mobileClient.features.members.repository.entity.Member
import com.example.backend.mobileClient.features.membership.repository.entity.PaymentPlan
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(name = "contract")
data class Contract(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    val consultant: Employee,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_plan_id", nullable = false)
    val plan: PaymentPlan,

    @Enumerated(EnumType.STRING)
    val type: ContractType = ContractType.MAIN,

    @Column(name = "sign_up_date")
    @Temporal(TemporalType.DATE)
    val signUpDate: LocalDate = LocalDate.now(),

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    val startDate: LocalDate = LocalDate.now(),

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    val endDate: LocalDate,

    @Column(name = "commitment_end_date")
    @Temporal(TemporalType.DATE)
    val commitmentEndDate: LocalDate,

    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod,

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    val totalAmount: BigDecimal,

    @Column(name = "total_tax", nullable = false, precision = 10, scale = 2)
    val totalTax: BigDecimal,

    @Column(name = "total_amount_without_tax", nullable = false, precision = 10, scale = 2)
    val totalAmountWithoutTax: BigDecimal,

    @Column(name = "total_paid", nullable = false, precision = 10, scale = 2)
    val totalPaid: BigDecimal,

    @Column(name = "remaining_amount", nullable = false, precision = 10, scale = 2)
    val remainingAmount: BigDecimal,

    @Enumerated(EnumType.STRING)
    val status: ContractStatus = ContractStatus.ACTIVE,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    ) {
    fun toDto() = ContractDto(
        id = 1,
        member = member,
        consultant = consultant,
        plan = plan,
        type = type,
        signUpDate = signUpDate,
        startDate = startDate,
        endDate = endDate,
        commitmentEndDate = commitmentEndDate,
        paymentMethod = paymentMethod,
        totalAmount = totalAmount,
        totalTax = totalTax,
        totalAmountWithoutTax = totalAmountWithoutTax,
        totalPaid = totalPaid,
        remainingAmount = remainingAmount,
        status = status,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

}
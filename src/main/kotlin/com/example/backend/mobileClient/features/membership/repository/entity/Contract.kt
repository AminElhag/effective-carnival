package com.example.backend.mobileClient.features.membership.repository.entity

import com.example.backend.mobileClient.common.ContractStatus
import com.example.backend.mobileClient.common.ContractType
import com.example.backend.mobileClient.common.PaymentMethod
import com.example.backend.mobileClient.features.employee.repository.entity.Employee
import com.example.backend.mobileClient.features.members.repository.entity.Member
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

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

    @Column(name = "first_payment_amount", nullable = false, precision = 10, scale = 2)
    val firstPaymentAmount: BigDecimal,

    @Enumerated(EnumType.STRING)
    val status: ContractStatus = ContractStatus.ACTIVE,

    ) {

}

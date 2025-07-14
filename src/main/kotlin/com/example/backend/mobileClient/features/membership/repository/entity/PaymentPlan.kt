package com.example.backend.mobileClient.features.membership.repository.entity

import com.example.backend.mobileClient.common.PaymentMethod
import com.example.backend.mobileClient.common.PeriodType
import com.example.backend.mobileClient.common.PlanType
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "payment_plans")
data class PaymentPlan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @NotBlank(message = "Plan name is required")
    @Size(min = 3, max = 100, message = "Plan name must be between 3-100 characters")
    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "fiscal_name", unique = true)
    val fiscalName: String? = null,

    @Column(name = "description", columnDefinition = "TEXT")
    val description: String? = null,

    @Positive(message = "Must be positive")
    @Column(name = "minimum_age")
    val minimumAge: Int? = null,

    @Positive(message = "Must be positive")
    @Column(name = "max_age")
    val maxAge: Int? = null,

    @Column(name = "active")
    val active: Boolean = false,

    @Enumerated(EnumType.STRING)
    @Column(name = "plan_type")
    val planType: PlanType = PlanType.CONTRACT,

    //Fees
    @Positive(message = "Membership must be positive")
    @Column(name = "membership_fee", nullable = false, precision = 10, scale = 2)
    val membershipFee: BigDecimal = BigDecimal.ZERO,

    @Positive(message = "Tax must be positive")
    @Column(name = "membership_fee_tax", nullable = false, precision = 10, scale = 2)
    val membershipFeeTax: BigDecimal = BigDecimal.ZERO,

    @Positive(message = "Administration fee must be positive")
    @Column(name = "administration_fee", nullable = false, precision = 10, scale = 2)
    val administrationFee: BigDecimal = BigDecimal.ZERO,

    @Positive(message = "Tax must be positive")
    @Column(name = "administration_fee_tax", nullable = false, precision = 10, scale = 2)
    val administrationFeeTax: BigDecimal = BigDecimal.ZERO,

    @Positive(message = "Joining fee must be positive")
    @Column(name = "joining_fee", nullable = false, precision = 10, scale = 2)
    val joiningFee: BigDecimal = BigDecimal.ZERO,

    @Positive(message = "Joining fee tax must be positive")
    @Column(name = "joining_fee_tax", nullable = false, precision = 10, scale = 2)
    val joiningFeeTax: BigDecimal = BigDecimal.ZERO,

    //Payment methods
    @NotNull(message = "PaymentPlan must be set")
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_plan_type")
    val paymentMethods: List<PaymentMethod> = emptyList(),

    //Periods
    @NotNull(message = "Payment interval is required")
    @Column(name = "payment_interval")
    val paymentInterval: Int = 1,

    @NotNull(message = "Payment interval Type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_interval_type")
    val paymentIntervalType: PeriodType = PeriodType.DAY,

    @NotNull(message = "Commitment period is required")
    @Column(name = "commitment_period")
    val commitmentPeriod: Int = 1,

    @NotNull(message = "Commitment Period Type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "commitment_period_type")
    val commitmentPeriodType: PeriodType = PeriodType.DAY,

    @Column(name = "minimum_cancellation_period")
    val minimumCancellationPeriod: Int? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "minimum_cancellation_period_tType")
    val minimumCancellationPeriodType: PeriodType? = null,

    @Column(name = "maximum_cancellation_period")
    val maximumCancellationPeriod: Int? = null,

    @NotNull(message = "Commitment Period Type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "maximum_cancellation_period_type")
    val maximumCancellationPeriodType: PeriodType? = null,

    @Column(name = "first_payment_on_sign_up_date")
    val firstPaymentOnSignUpDate: Boolean = false,

    @Column(name = "automatically_end_contract_after_commitment_period_reached")
    val automaticallyEndContractAfterCommitmentPeriodReached: Boolean = false,

    @Column("is_limit_number_of_visits_in_a_period")
    val isLimitNumberVisitsInThePeriod: Boolean = false,

    @Column("limit_number_of_visits_in_a_period")
    val limitNumberVisitsInThePeriod: Int = 0,

    @Column("can_be_renewed")
    val canBeRenewed: Boolean = false,

    @Column("automatic_renew")
    val automaticRenew: Boolean = false,

    @OneToMany()
    @Column(name = "agreements")
    val agreements: List<Agreement> = emptyList(),

    @CreationTimestamp
    @Column(name = "start_date")
    val startDate: LocalDateTime? = null,

    @CreationTimestamp
    @Column(name = "end_date")
    val endDate: LocalDateTime? = null,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {}
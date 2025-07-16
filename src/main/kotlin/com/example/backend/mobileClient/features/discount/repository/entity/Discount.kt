package com.example.backend.mobileClient.features.discount.repository.entity

import com.example.backend.mobileClient.common.DiscountType
import com.example.backend.mobileClient.features.membership.repository.entity.PaymentPlan
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.hibernate.validator.constraints.Length
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class Discount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @NotNull(message = "Name is required")
    @Column(name = "name")
    val name: String,

    @Column(name = "active")
    val active: Boolean = true,

    @Column(name = "membership_fee")
    val membershipFee:Boolean = false,

    @Column(name = "membership_fee_discount")
    val membershipFeeDiscount: BigDecimal = BigDecimal.ZERO,

    @Column(name = "membership_fee_discount_type")
    val membershipFeeDiscountType: DiscountType? = null,

    @Column(name = "has_admin_fee")
    val hasAdminFee:Boolean = false,

    @Column(name = "admin_fee")
    val adminFee: BigDecimal = BigDecimal.ZERO,

    @Column(name = "admin_fee_discount_type")
    val adminFeeDiscountType: DiscountType? = null,

    @Column(name = "has_join_fee")
    val hasJoinFee:Boolean = false,

    @Column(name = "join_fee")
    val joinFee: BigDecimal = BigDecimal.ZERO,

    @Column(name = "join_fee_discount_type")
    val joinFeeDiscountType: DiscountType? = null,

    @ManyToMany
    @Column(name = "payment_plans")
    val paymentPlans:Set<PaymentPlan> = setOf(),

    @Column(name = "all_payment_plans")
    val allPaymentPlans: Boolean = false,

    @Column(name = "has_promo_code")
    val hasPromoCode:Boolean = false,

    @Length(max = 8, min = 8, message = "PromoCode must be 8 characters long")
    @Column(name = "promo_code")
    val promoCode: String? = null,

    @Column(name = "one_per_user")
    val onePerUser: Boolean = true,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "discount_incompatibilities",
        joinColumns = [JoinColumn(name = "discount_id")],
        inverseJoinColumns = [JoinColumn(name = "incompatible_discount_id")]
    )
    val incompatibleDiscounts: Set<Discount> = emptySet(),

    @CreationTimestamp
    @Column(name = "start_date")
    val startDate: LocalDate? = null,

    @CreationTimestamp
    @Column(name = "end_date")
    val endDate: LocalDate? = null,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)

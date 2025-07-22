package com.example.backend.mobileClient.features.contract.repository

import com.example.backend.mobileClient.common.PaymentProvider
import com.example.backend.mobileClient.common.PaymentStatus
import com.example.backend.mobileClient.features.members.repository.entity.Member
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "payments")
data class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "transaction_id", nullable = false, unique = true)
    val transactionId: String,

    @Column(name = "masked_card_number", nullable = false)
    val maskedCardNumber: String,

    /*@Column(name = "card_holder_name", nullable = false)
    val cardholderName: String,*/

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_provider", nullable = false)
    val paymentProvider: PaymentProvider,

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    val amount: BigDecimal,

    @Column(name = "currency", nullable = false)
    val currency: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    val status: PaymentStatus,

    @Column(name = "processor_response")
    val processorResponse: String? = null,

    @OneToOne
    @JoinColumn(name = "contract_id", nullable = false)
    val contract: Contract,

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)

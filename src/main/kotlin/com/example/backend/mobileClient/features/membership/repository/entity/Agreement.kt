package com.example.backend.mobileClient.features.membership.repository.entity

import com.example.backend.mobileClient.features.membership.controller.models.AgreementResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "agreements")
data class Agreement(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @NotNull(message = "title is required")
    @Column(name = "title")
    val title: String,

    //@Lob
    @Column(name = "body",columnDefinition = "TEXT")
    val body: String,

    @Column(name="required")
    val required: Boolean = true,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
    fun toResponse() = AgreementResponse(
        id = id,
        title = title,
        body = body,
        required = true
    )
}

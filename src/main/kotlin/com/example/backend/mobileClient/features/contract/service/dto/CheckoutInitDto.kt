package com.example.backend.mobileClient.features.contract.service.dto

import kotlinx.datetime.LocalDate

data class CheckoutInitDto(
    val membershipId: Long? = null,
    val startDate: LocalDate? = null,
    val promoCode: String? = null,
    val acceptAgreementsIds: Set<Long>?
)
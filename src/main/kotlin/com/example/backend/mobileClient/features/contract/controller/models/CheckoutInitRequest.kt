package com.example.backend.mobileClient.features.contract.controller.models

import com.example.backend.mobileClient.features.contract.service.dto.CheckoutInitDto
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckoutInitRequest(
    @SerialName("membership_id")
    val membershipId: Long,
    @SerialName("start_date")
    val startDate: LocalDate,
    @SerialName("promo_code")
    val promoCode: String?,
    @SerialName("accept_agreements_ids")
    val acceptAgreementsIds: Set<Long>?
) {
    fun toDto()= CheckoutInitDto(
        membershipId = membershipId,
        startDate = startDate,
        promoCode = promoCode,
        acceptAgreementsIds = acceptAgreementsIds
    )
}

package com.example.backend.mobileClient.features.membership.controller.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MembershipResponse(
    @SerialName("membership_plans")
    val plans:List<MembershipPlan>
)

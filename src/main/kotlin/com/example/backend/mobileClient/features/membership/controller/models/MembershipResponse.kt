package com.example.backend.mobileClient.features.membership.controller.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MembershipResponse(
    @SerialName("plans")
    val plans: List<Plan>,
)

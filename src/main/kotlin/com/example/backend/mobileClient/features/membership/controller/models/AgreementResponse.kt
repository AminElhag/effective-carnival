package com.example.backend.mobileClient.features.membership.controller.models

import kotlinx.serialization.Serializable

@Serializable
data class AgreementResponse(
    val id: Long = 0,

    val title: String,

    val body: String,

    val required: Boolean,
)
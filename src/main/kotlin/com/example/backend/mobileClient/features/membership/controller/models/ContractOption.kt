package com.example.backend.mobileClient.features.membership.controller.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContractOption(
    @SerialName("id")
    var id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("default")
    val default: Boolean = false,
)
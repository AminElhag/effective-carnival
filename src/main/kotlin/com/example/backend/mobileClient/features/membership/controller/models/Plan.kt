package com.example.backend.mobileClient.features.membership.controller.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Plan(
    @SerialName("id")
    var id: String,
    @SerialName("title")
    val title: String,
    @SerialName("price")
    val price: String,
    @SerialName("features")
    val features: List<String>,
    @SerialName("contract_options")
    val contractOptions: List<ContractOption> = emptyList(),
)
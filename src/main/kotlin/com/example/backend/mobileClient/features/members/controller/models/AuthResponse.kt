package com.example.backend.mobileClient.features.members.controller.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    @SerialName("token")
    val token: String,
    /*@SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,*/
)
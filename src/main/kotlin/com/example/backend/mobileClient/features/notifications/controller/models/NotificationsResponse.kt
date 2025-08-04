package com.example.backend.mobileClient.features.notifications.controller.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationsResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("time")
    val time: String,
)

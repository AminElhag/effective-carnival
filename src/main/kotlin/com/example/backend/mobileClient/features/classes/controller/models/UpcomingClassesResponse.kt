package com.example.backend.mobileClient.features.classes.controller.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpcomingClassesResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("instructor")
    val instructor: String,
    @SerialName("image_url")
    val imageUrl: String,
)
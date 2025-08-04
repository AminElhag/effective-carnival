package com.example.backend.mobileClient.features.banners.controller.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("deep_link")
    val deepLink: String? = null,
)

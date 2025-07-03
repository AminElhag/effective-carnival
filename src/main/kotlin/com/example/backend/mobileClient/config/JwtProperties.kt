package com.example.backend.mobileClient.config

import jakarta.annotation.PostConstruct
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    var secret: String = "",
    var expirationMs: Long = 0,
    var issuer: String = ""
) {
    @PostConstruct
    fun validate() {
        require(secret.length >= 64) { "JWT secret must be at least 64 characters" }
    }
}
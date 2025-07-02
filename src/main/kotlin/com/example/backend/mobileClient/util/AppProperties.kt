package com.example.backend.mobileClient.util

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConfigurationProperties(prefix = "app")
@Configuration
data class AppProperties(
    var prefix: String = "1010"
)
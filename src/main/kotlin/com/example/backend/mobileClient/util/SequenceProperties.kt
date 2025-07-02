package com.example.backend.mobileClient.util

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "app.sequence.prefix")
data class SequenceProperties(
    var prefix: String = "1010"
) {
    init {
        require(prefix.matches(Regex("\\d{4}"))) {
            "Prefix must be 4 digits"
        }
    }
}
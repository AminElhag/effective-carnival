package com.example.backend.mobileClient.common

import com.example.backend.mobileClient.features.members.repository.MemberRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CodeGeneratorService(
    @Value("\${app.sequence.prefix}") private val codePrefix: String,
    private val repository: MemberRepository
) {
    private val maxSequence = 9999

    @Transactional
    fun generateNextCode(): String {
        val lastEntity = repository.findTopByPublicIdStartingWithOrderByPublicIdDesc(codePrefix)
        val lastNumber = lastEntity?.publicId?.removePrefix(codePrefix)?.toIntOrNull() ?: -1
        val nextNumber = (lastNumber + 1).coerceAtMost(maxSequence)
        return "$codePrefix${String.format("%04d", nextNumber)}"
    }
}

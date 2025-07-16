package com.example.backend.mobileClient.common.Service

import com.example.backend.mobileClient.features.members.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserDetailsServiceImpl(
    private val userRepository: MemberRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails? {
        return userRepository.findByPublicId((username)) ?: throw UsernameNotFoundException("User $username not found")
    }
}
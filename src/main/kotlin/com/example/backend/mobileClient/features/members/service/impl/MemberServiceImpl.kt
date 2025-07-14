package com.example.backend.mobileClient.features.members.service.impl

import com.example.backend.mobileClient.features.members.controller.models.AuthResponse
import com.example.backend.mobileClient.features.members.repository.MemberRepository
import com.example.backend.mobileClient.features.members.service.MemberService
import com.example.backend.mobileClient.features.members.service.dto.MemberDto
import com.example.backend.mobileClient.common.CodeGeneratorService
import com.example.backend.mobileClient.util.JwtUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl : MemberService {

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var publicIdGenerator: CodeGeneratorService

    @Autowired
    lateinit var encryptedPassword: PasswordEncoder

    @Autowired
    lateinit var jwtUtils: JwtUtils

    @Autowired
    lateinit var authManager: AuthenticationManager

    @Autowired
    lateinit var userDetailsService: UserDetailsService

    override fun createNewClient(user: MemberDto): AuthResponse {
        val save = memberRepository.save(
            user.toEntity(
                publicIdGenerator.generateNextCode(),
                encryptedPassword.encode(user.password)
            )
        )
        val userDetails = userDetailsService.loadUserByUsername(save.publicId)
        val token = jwtUtils.generateToken(userDetails)
        return AuthResponse(
            token = token,
        )
    }
}
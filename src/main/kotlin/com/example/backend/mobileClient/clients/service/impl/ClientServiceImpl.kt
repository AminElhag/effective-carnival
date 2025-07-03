package com.example.backend.mobileClient.clients.service.impl

import com.example.backend.mobileClient.clients.controller.models.AuthResponse
import com.example.backend.mobileClient.clients.repository.ClientRepository
import com.example.backend.mobileClient.clients.service.ClientService
import com.example.backend.mobileClient.clients.service.dto.UserDto
import com.example.backend.mobileClient.common.CodeGeneratorService
import com.example.backend.mobileClient.util.JwtUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl : ClientService {

    @Autowired
    lateinit var clientRepository: ClientRepository

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

    override fun createNewClient(user: UserDto): AuthResponse {
        val save = clientRepository.save(
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
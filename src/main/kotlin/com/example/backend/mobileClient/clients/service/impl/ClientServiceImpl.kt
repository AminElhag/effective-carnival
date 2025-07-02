package com.example.backend.mobileClient.clients.service.impl

import com.example.backend.mobileClient.clients.repository.ClientRepository
import com.example.backend.mobileClient.clients.service.ClientService
import com.example.backend.mobileClient.clients.service.dto.UserDto
import com.example.backend.mobileClient.common.CodeGeneratorService

import org.springframework.beans.factory.annotation.Autowired
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

    override fun createNewClient(user: UserDto) {
        clientRepository.save(
            user.toEntity(
                publicIdGenerator.generateNextCode(),
                encryptedPassword.encode(user.password)
            )
        )
    }
}
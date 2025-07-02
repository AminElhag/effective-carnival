package com.example.backend.mobileClient.clients.service

import com.example.backend.mobileClient.clients.service.dto.UserDto

interface ClientService {
    fun createNewClient(user: UserDto)
}
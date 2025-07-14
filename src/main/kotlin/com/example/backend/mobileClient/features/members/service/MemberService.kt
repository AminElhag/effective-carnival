package com.example.backend.mobileClient.features.members.service

import com.example.backend.mobileClient.features.members.controller.models.AuthResponse
import com.example.backend.mobileClient.features.members.service.dto.MemberDto

interface MemberService {
    fun createNewClient(user: MemberDto): AuthResponse
}
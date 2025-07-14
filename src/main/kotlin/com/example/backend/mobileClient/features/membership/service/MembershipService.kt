package com.example.backend.mobileClient.features.membership.service

import com.example.backend.mobileClient.features.membership.controller.models.MembershipResponse

interface MembershipService {
    fun getMemberships(): List<MembershipResponse>
}
package com.example.backend.mobileClient.features.membership.service

import com.example.backend.mobileClient.features.membership.service.dto.PaymentPlanDto

interface MembershipService {
    fun getMemberships(): List<PaymentPlanDto>
}
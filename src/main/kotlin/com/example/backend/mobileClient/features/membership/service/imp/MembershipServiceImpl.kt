package com.example.backend.mobileClient.features.membership.service.imp

import com.example.backend.mobileClient.features.membership.service.MembershipService
import com.example.backend.mobileClient.features.membership.service.dto.PaymentPlanDto
import com.example.backend.mobileClient.plans
import org.springframework.stereotype.Service

@Service
class MembershipServiceImpl : MembershipService {


    override fun getMemberships(): List<PaymentPlanDto> {
        return plans.map {
            it.toDto()
        }
    }

}

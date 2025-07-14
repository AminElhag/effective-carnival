package com.example.backend.mobileClient.features.membership.service.imp

import com.example.backend.mobileClient.features.membership.controller.models.MembershipResponse
import com.example.backend.mobileClient.features.membership.service.MembershipService
import org.springframework.stereotype.Service

@Service
class MembershipServiceImpl : MembershipService {

    override fun getMemberships(): List<MembershipResponse> {
        TODO("Not yet implemented")
    }

}
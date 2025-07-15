package com.example.backend.mobileClient.features.membership.controller

import com.example.backend.mobileClient.features.membership.controller.models.MembershipResponse
import com.example.backend.mobileClient.features.membership.service.MembershipService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/membership")
class MembershipController {

    @Autowired
    lateinit var membershipService: MembershipService

    @GetMapping()
    fun getShortMembership(): ResponseEntity<MembershipResponse> {
        return ResponseEntity.ok(
            MembershipResponse(membershipService.getMemberships().map {
                it.toMembershipResponse()
            })
        )
    }
}
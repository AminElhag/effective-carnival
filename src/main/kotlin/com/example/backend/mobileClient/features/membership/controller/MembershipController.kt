package com.example.backend.mobileClient.features.membership.controller

import com.example.backend.mobileClient.features.membership.controller.models.ContractOption
import com.example.backend.mobileClient.features.membership.controller.models.MembershipResponse
import com.example.backend.mobileClient.features.membership.controller.models.Plan
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

    @GetMapping
    fun getMembership(): ResponseEntity<MembershipResponse> {
        val response: List<MembershipResponse> = membershipService.getMemberships()
        return ResponseEntity.ok(
            MembershipResponse(
                listOf(
                    Plan(
                        id = "1",
                        "Basic",
                        "$29 /month",
                        listOf(
                            "✓ Access to basic gym facilities",
                            "✓ Group fitness classes",
                            "✓ Personalized workout plans"
                        ),
                        contractOptions = listOf(
                            ContractOption(
                                id = "2",
                                title = "Month-to-month",
                                description = "Month-to-month",
                                default = true
                            ),
                        )
                    ),
                    Plan(
                        id = "2",
                        "Pro",
                        "$49 /month",
                        listOf(
                            "✓ All Basic features",
                            "✓ Advanced training equipment",
                            "✓ Nutrition guidance"
                        ),
                        contractOptions = listOf(
                            ContractOption(
                                id = "1",
                                title = "12-month contract",
                                description = "12 months",
                                default = true,
                            ),
                        )
                    ),
                    Plan(
                        id = "3",
                        "Premium",
                        "$79 /month",
                        listOf(
                            "✓ All Pro features",
                            "✓ Exclusive gym areas",
                            "✓ Priority booking for classes"
                        ),
                        contractOptions = listOf(
                            ContractOption(
                                id = "1",
                                title = "12-month contract",
                                description = "12 months",
                                default = true,
                            ),
                            ContractOption(
                                id = "2",
                                title = "Month-to-month",
                                description = "Month-to-month",
                            ),
                        )
                    )
                )
            )
        )
    }
}
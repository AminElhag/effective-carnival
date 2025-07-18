package com.example.backend.mobileClient.features.contract.controller

import com.example.backend.mobileClient.features.contract.controller.models.CheckoutInitRequest
import com.example.backend.mobileClient.features.contract.controller.models.CheckoutInitResponse
import com.example.backend.mobileClient.features.contract.service.ContractService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/contract")
class ContractController {

    @Autowired
    lateinit var service: ContractService

    @PostMapping("/checkout")
    fun checkoutInit(
        @RequestBody request: CheckoutInitRequest
    ): ResponseEntity<CheckoutInitResponse> {
        return ResponseEntity.ok().body(service.checkoutInit(request.toDto()).toCheckoutInit())
    }
}
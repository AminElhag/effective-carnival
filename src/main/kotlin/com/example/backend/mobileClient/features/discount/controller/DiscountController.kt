package com.example.backend.mobileClient.features.discount.controller

import com.example.backend.mobileClient.features.discount.controller.model.ValidateDiscountResponse
import com.example.backend.mobileClient.features.discount.service.DiscountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/discounts")
class DiscountController {

    @Autowired
    lateinit var discountService: DiscountService

    @GetMapping("/validate")
    fun validateDiscount(
        @RequestParam code: String,
        @RequestParam("payment_plan_id") paymentPlanId: Long,
    ): ResponseEntity<ValidateDiscountResponse>{
        val validate = discountService.validate(code, paymentPlanId)
        return ok(validate.toResponse())
    }
}
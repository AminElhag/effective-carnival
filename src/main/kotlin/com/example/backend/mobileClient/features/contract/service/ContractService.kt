package com.example.backend.mobileClient.features.contract.service

import com.example.backend.mobileClient.features.contract.controller.models.PaymentResponse
import com.example.backend.mobileClient.features.contract.service.dto.CheckoutInitDto
import com.example.backend.mobileClient.features.contract.service.dto.ContractDto
import com.example.backend.mobileClient.features.contract.service.dto.PaymentDto

interface ContractService {
    fun checkoutInit(toDto: CheckoutInitDto): ContractDto
    fun processPayment(paymentDto: PaymentDto): PaymentResponse?
}
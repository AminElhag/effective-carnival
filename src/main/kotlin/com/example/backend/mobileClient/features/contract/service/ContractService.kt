package com.example.backend.mobileClient.features.contract.service

import com.example.backend.mobileClient.features.contract.service.dto.CheckoutInitDto
import com.example.backend.mobileClient.features.contract.service.dto.ContractDto

interface ContractService {
    fun checkoutInit(toDto: CheckoutInitDto): ContractDto
}
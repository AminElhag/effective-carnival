package com.example.backend.mobileClient.features.contract.service.impl

import com.example.backend.mobileClient.features.contract.service.dto.PaymentDto
import com.example.backend.mobileClient.features.contract.service.dto.PaymentProcessorDto
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class PaymentProcessorService {


    fun processPayment(request: PaymentDto): PaymentProcessorDto {

        return try {
            Thread.sleep(1000)

            val isSuccess = Random.nextDouble() > 0.5

            if (isSuccess) {
                PaymentProcessorDto(
                    success = true,
                    transactionId = generateTransactionId(),
                    message = "Payment processed successfully",
                    processorReference = "REF_${System.currentTimeMillis()}"
                )
            } else {
                PaymentProcessorDto(
                    success = false,
                    transactionId = null,
                    message = "Payment declined by processor",
                    errorCode = "DECLINED"
                )
            }
        } catch (e: Exception) {
            PaymentProcessorDto(
                success = false,
                transactionId = null,
                message = "Payment processing failed: ${e.message}",
                errorCode = "PROCESSING_ERROR"
            )
        }
    }

    private fun generateTransactionId(): String {
        return "TXN_${System.currentTimeMillis()}_${Random.nextInt(1000, 9999)}"
    }
}
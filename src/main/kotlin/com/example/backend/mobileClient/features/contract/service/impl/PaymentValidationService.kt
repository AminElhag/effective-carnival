package com.example.backend.mobileClient.features.contract.service.impl

import com.example.backend.mobileClient.common.PaymentProvider
import com.example.backend.mobileClient.features.contract.service.dto.PaymentDto
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.springframework.stereotype.Service

@Service
class PaymentValidationService {

    fun validatePaymentRequest(request: PaymentDto): List<String> {
        val errors = mutableListOf<String>()

        // Validate card number using Luhn algorithm
        if (!isValidCardNumber(request.cardNumber.replace("\\s".toRegex(), ""))) {
            errors.add("Invalid card number")
        }

        // Validate expiration date
        if (!isValidExpirationDate(request.expirationDate)) {
            errors.add("Card has expired or invalid expiration date")
        }

        // Validate CVV
        if (!isValidCVV(request.cardCVV)) {
            errors.add("Invalid CVV")
        }

        return errors
    }

    private fun isValidCardNumber(cardNumber: String): Boolean {
        val digits = cardNumber.filter { it.isDigit() }
        if (digits.length < 13 || digits.length > 19) return false

        return luhnCheck(digits)
    }

    private fun luhnCheck(cardNumber: String): Boolean {
        var sum = 0
        var alternate = false

        for (i in cardNumber.length - 1 downTo 0) {
            var digit = cardNumber[i].digitToIntOrNull() ?: return false

            if (alternate) {
                digit *= 2
                if (digit > 9) {
                    digit = (digit % 10) + 1
                }
            }
            sum += digit
            alternate = !alternate
        }

        return sum % 10 == 0
    }

    private fun isValidExpirationDate(expiration: String): Boolean {
        if (!expiration.matches(Regex("\\d{2}/\\d{2}"))) return false

        val parts = expiration.split("/")
        val month = parts[0].toIntOrNull() ?: return false
        val year = parts[1].toIntOrNull() ?: return false

        if (month < 1 || month > 12) return false

        val fullYear = 2000 + year
        val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val currentYear = now.year
        val currentMonth = now.monthNumber

        return if (fullYear > currentYear) {
            true
        } else if (fullYear == currentYear) {
            month >= currentMonth
        } else {
            false
        }
    }

    private fun isValidCVV(cvv: String): Boolean {
        return cvv.length in 3..4 && cvv.all { it.isDigit() }
    }

    fun getPaymentProvider(cardNumber: String): PaymentProvider {
        val digits = cardNumber.replace("\\s".toRegex(), "")

        return when {
            digits.startsWith("4") -> PaymentProvider.VISA
            digits.startsWith("5") || digits.startsWith("2") -> PaymentProvider.MASTERCARD
            digits.startsWith("3") -> PaymentProvider.AMEX
            digits.startsWith("6") -> PaymentProvider.DISCOVER
            else -> PaymentProvider.UNKNOWN
        }
    }
}
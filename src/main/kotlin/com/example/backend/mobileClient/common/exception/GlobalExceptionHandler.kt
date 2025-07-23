package com.example.backend.mobileClient.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFound(ex: UserNotFoundException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = "User Not Found",
            message = ex.message ?: "User not found",
            timestamp = LocalDateTime.now()
        )
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IncorrectPasswordException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleIncorrectPassword(ex: IncorrectPasswordException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.UNAUTHORIZED.value(),
            error = "Incorrect Password",
            message = ex.message ?: "Incorrect Password",
            timestamp = LocalDateTime.now()
        )
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }
}
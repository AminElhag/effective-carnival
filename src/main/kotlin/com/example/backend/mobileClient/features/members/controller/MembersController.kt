package com.example.backend.mobileClient.features.members.controller

import com.example.backend.mobileClient.features.members.controller.models.AuthResponse
import com.example.backend.mobileClient.features.members.controller.models.UserRequest
import com.example.backend.mobileClient.features.members.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/client")
class MembersController {

    @Autowired
    lateinit var clientService: MemberService

    @GetMapping
    fun welcome() : String {
        return "Welcome"
    }

    @PostMapping("/register")
    fun registerClient(
        @RequestBody request: UserRequest
    ) : ResponseEntity<AuthResponse> {
        val createNewClient = clientService.createNewClient(request.toDto())
        println(createNewClient.token)
        return ResponseEntity.ok(createNewClient)
    }
}
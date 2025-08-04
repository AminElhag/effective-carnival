package com.example.backend.mobileClient.features.classes.controller

import com.example.backend.mobileClient.features.classes.controller.models.UpcomingClassesResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/classes")
class ClassesController {

    @GetMapping("/upcoming")
    fun getUpcomingClasses() : ResponseEntity<List<UpcomingClassesResponse>> {
        return ResponseEntity.ok(
            listOf(
                UpcomingClassesResponse(
                    id = 1L,
                    title = "Upcoming title",
                    instructor = "Amin",
                    imageUrl = "https://images.unsplash.com/photo-1613323593608-abc90fec84ff?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                UpcomingClassesResponse(
                    id = 1L,
                    title = "Upcoming title",
                    instructor = "Amin",
                    imageUrl = "https://images.unsplash.com/photo-1613323593608-abc90fec84ff?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                UpcomingClassesResponse(
                    id = 1L,
                    title = "Upcoming title",
                    instructor = "Amin",
                    imageUrl = "https://images.unsplash.com/photo-1613323593608-abc90fec84ff?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
            )
        )
    }
}
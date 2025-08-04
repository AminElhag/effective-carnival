package com.example.backend.mobileClient.features.notifications.controller

import com.example.backend.mobileClient.features.notifications.controller.models.NotificationsResponse
import kotlinx.datetime.Clock
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/notifications")
class NotificationsController {

    @GetMapping("/count")
    fun getNotificationsCount(): ResponseEntity<Int> {
        return ResponseEntity.ok(0)
    }

    @GetMapping()
    fun getNotifications(): ResponseEntity<List<NotificationsResponse>> {
        return ResponseEntity.ok(
            listOf(
                NotificationsResponse(
                    id = 1,
                    title = "Workout Plan Updated",
                    description = "Your workout plan for the week has been updated. Check out the new exercises and schedule.",
                    time = Clock.System.now().toString(),
                ), NotificationsResponse(
                    id = 1,
                    title = "Training Session Reminder",
                    description = "Reminder: Your personal training session with Coach Alex is scheduled for tomorrow at 10 AM.",
                    time = Clock.System.now().toString(),
                ), NotificationsResponse(
                    id = 1,
                    title = "New Content Available",
                    description = "New fitness tips and nutrition advice are available in the app. Explore the latest articles.",
                    time = Clock.System.now().toString(),
                ), NotificationsResponse(
                    id = 1,
                    title = "Monthly Progress Report",
                    description = "Your progress report for the last month is ready. Review your achievements and set new goals.",
                    time = Clock.System.now().toString(),
                ), NotificationsResponse(
                    id = 1,
                    title = "Fitness Challenge Announcement",
                    description = "Join our upcoming fitness challenge and compete with other members for exciting prizes.",
                    time = Clock.System.now().toString(),
                ), NotificationsResponse(
                    id = 1,
                    title = "App Update Available",
                    description = "We've added new features to enhance your experience. Update the app to enjoy the latest improvements.",
                    time = Clock.System.now().toString(),
                )
            )
        )
    }
}
package com.example.backend.mobileClient.features.banners.controller

import com.example.backend.mobileClient.features.banners.controller.models.BannerResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/banners")
class BannersController {

    @GetMapping
    fun getBanners(): ResponseEntity<List<BannerResponse>>{
        return ResponseEntity.ok().body(
            listOf(
                BannerResponse(
                    id = 1L,
                    title = "New Equipment",
                    description = "Explore our latest additions",
                    imageUrl = "https://plus.unsplash.com/premium_photo-1664474619075-644dd191935f?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                BannerResponse(
                    id = 2L,
                    title = "Special Offers",
                    description = "Limited time discounts on memberships",
                    imageUrl = "https://plus.unsplash.com/premium_photo-1664474619075-644dd191935f?q=80&w=2069&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
            )
        )
    }
}
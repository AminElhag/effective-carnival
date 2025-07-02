package com.example.backend.mobileClient.clients.repository

import com.example.backend.mobileClient.clients.repository.entity.Client
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ClientRepository: CrudRepository<Client, UUID> {
    fun findTopByPublicIdStartingWithOrderByPublicIdDesc(prefix: String): Client?
}
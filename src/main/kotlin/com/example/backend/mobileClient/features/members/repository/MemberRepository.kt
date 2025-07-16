package com.example.backend.mobileClient.features.members.repository

import com.example.backend.mobileClient.features.members.repository.entity.Member
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MemberRepository: CrudRepository<Member, Long> {
    fun findTopByPublicIdStartingWithOrderByPublicIdDesc(prefix: String): Member?
    fun findByPublicId(clientId: String): Member?
}
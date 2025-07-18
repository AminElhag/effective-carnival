package com.example.backend.mobileClient.features.members.service.dto

import com.example.backend.mobileClient.features.members.repository.entity.Member
import java.time.LocalDate
import java.time.LocalDateTime

data class MemberDto(
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val idNumber: String,
    val dataOfBirth: LocalDate?,
    val genderId: Int,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val emergencyContact: String,
    val hearAboutUsId: Int,
    val occupation: String,
    val medicalConditionsIds: List<Int>,
) {


    fun toEntity(
        publicId: String,
        encryptedPassword: String,
    ) = Member(
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        idNumber = idNumber,
        dataOfBirth = dataOfBirth,
        genderId = genderId,
        phoneNumber = phoneNumber,
        email = email,
        password = encryptedPassword,
        emergencyContact = emergencyContact,
        hearAboutUsId = hearAboutUsId,
        occupation = occupation,
        medicalConditionsIds = medicalConditionsIds,
        publicId = publicId,
    )
}

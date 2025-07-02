package com.example.backend.mobileClient.clients.controller.models

import com.example.backend.mobileClient.clients.service.dto.UserDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class UserRequest(
    @SerialName("first_name")
    val firstName: String,
    @SerialName("middle_name")
    val middleName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("id_number")
    val idNumber: String,
    @SerialName("date_of_birth")
    val dataOfBirth: String,
    @SerialName("gender_id")
    val genderId: Int,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("email")
    val email: String,
    @SerialName("password")
    val password: String,
    @SerialName("emergency_contact")
    val emergencyContact: String,
    @SerialName("hear_about_us_id")
    val hearAboutUsId: Int,
    @SerialName("occupation")
    val occupation: String,
    @SerialName("medical_conditions_ids")
    val medicalConditionsIds: List<Int>,
) {
    fun toDto() = UserDto(
        firstName = firstName,
        middleName = middleName,
        lastName = lastName,
        idNumber = idNumber,
        dataOfBirth = LocalDateTime.parse(dataOfBirth),
        genderId = genderId,
        phoneNumber = phoneNumber,
        email = email,
        password = password,
        emergencyContact = emergencyContact,
        hearAboutUsId = hearAboutUsId,
        occupation = occupation,
        medicalConditionsIds = medicalConditionsIds,
    )
}

package com.example.backend.mobileClient.clients.repository.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import java.time.LocalDateTime
import java.util.*

@Entity(name = "client")
data class Client(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: UUID? = null,
    @Column(name = "client_id", nullable = false, unique = true)
    val publicId: String,
    @Column(name = "first_name", nullable = false, updatable = false)
    val firstName: String,
    @Column(name = "middle_name", updatable = false)
    val middleName: String? = null,
    @Column(name = "last_name", nullable = false, updatable = false)
    val lastName: String,
    @Column(name = "id_number", nullable = false, updatable = false) @Size(
        min = 10,
        max = 11,
        message = "Field length must be between 10 and 11"
    )
    val idNumber: String,
    @Column(name = "data_of_birth", nullable = false)
    val dataOfBirth: LocalDateTime?,
    @Column(name = "gender_id", nullable = false)
    val genderId: Int = 1,
    @Column(name = "phone_number", nullable = false, unique = true) @Size(
        max = 15,
        min = 7,
        message = "Field length must be between 7 and 15"
    )
    val phoneNumber: String,
    @Column(name = "email", nullable = false, unique = true) @Email
    val email: String,
    @Column(name = "password", nullable = false)
    val password: String,
    @Column(name = "emergency_contact", nullable = false)
    val emergencyContact: String,
    @Column(name = "hear_about_us_id", nullable = false)
    val hearAboutUsId: Int,
    @Column(name = "occupation", nullable = false)
    val occupation: String,
    @Column(name = "medical_conditions_ids") @Convert(converter = IntegerListConverter::class)
    val medicalConditionsIds: List<Int?>,
    @Column(name = "create_at")
    val createAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "update_at")
    val updateAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "is_deleted")
    val isDeleted: Boolean = false,
    @Column(name = "is_validation")
    val isValidation: Boolean = false,
) {

}

@Converter
class IntegerListConverter : AttributeConverter<List<Int>, String> {
    override fun convertToDatabaseColumn(attribute: List<Int>): String {
        return attribute.joinToString(",")
    }

    override fun convertToEntityAttribute(dbData: String): List<Int> {
        return dbData.split(",").map { it.toInt() }
    }
}
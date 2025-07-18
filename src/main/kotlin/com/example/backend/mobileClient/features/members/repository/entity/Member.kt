package com.example.backend.mobileClient.features.members.repository.entity

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(name = "member")
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

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
    val dataOfBirth: LocalDate?,
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
    private var password: String,
    @Column(name = "emergency_contact", nullable = false)
    val emergencyContact: String,
    @Column(name = "hear_about_us_id", nullable = false)
    val hearAboutUsId: Int,
    @Column(name = "occupation", nullable = false)
    val occupation: String,
    @ElementCollection
    @CollectionTable(
        name = "client_medical_conditions",
        joinColumns = [JoinColumn(name = "client_id")]
    )
    @Column(name = "condition_id")
    val medicalConditionsIds: List<Int?>,
    @Column(name = "is_deleted")
    val isDeleted: Boolean = false,
    @Column(name = "is_validation")
    val isValidation: Boolean = false,
    @Column(name = "roles")
    val roles: String = "ROLE_USER",
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now()
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return roles.split(",").map { SimpleGrantedAuthority(it.trim()) }
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return publicId
    }
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
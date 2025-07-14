package com.example.backend.mobileClient.features.employee.repository.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDate
import java.time.LocalDateTime


@Entity(name = "employee")
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "first_name", nullable = false, length = 50)
    val firstName: String,

    @Column(name = "last_name", nullable = false, length = 50)
    val lastName: String,

    @Column(name = "birthdate")
    val birthdate: LocalDate?,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    val gender: Gender?,

    @Column(name = "email", nullable = false, unique = true, length = 100)
    val email: String,

    @Column(name = "mobile_phone", length = 20)
    val mobilePhone: String?,

    @Column(name = "home_phone", length = 20)
    val homePhone: String?,

    @Column(name = "street", length = 100)
    val street: String?,

    @Column(name = "city", length = 50)
    val city: String?,

    @Column(name = "post_code", length = 20)
    val postCode: String?,

    @Column(name = "state", length = 50)
    val state: String?,

    // Employment Details
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type", length = 20)
    val employmentType: EmploymentType?,

    @Column(name = "employment_date")
    val employmentDate: LocalDate?,

    @Column(name = "available_in_new_clubs")
    val availableInNewClubs: Boolean = false,

    // System Information
    @Column(name = "system_role", nullable = false, length = 50)
    val systemRole: String,

    @Column(name = "position", nullable = false, length = 50)
    val position: String,

    @Column(name = "department", nullable = false, length = 50)
    val department: String,

    // Account Information
    @Column(name = "has_login_account")
    val hasLoginAccount: Boolean = false,

    @Column(name = "username", unique = true, length = 50)
    val username: String?,

    @JsonIgnore
    @Column(name = "password", length = 100)
    val password: String?,

    @Column(name = "profile_picture_path", length = 200)
    val profilePicturePath: String?,

    // Additional Settings
    @Column(name = "auto_deactivate")
    val autoDeactivate: Boolean = true,

    @Column(name = "employee_identifier", unique = true, length = 50)
    val employeeIdentifier: String?,

    @Column(name = "marketing_newsletters")
    val marketingNewsletters: Boolean = false,

    @Column(name = "active")
    val active: Boolean = true,

    @CreatedDate
    @Column(updatable = false)
    val createdDate: LocalDateTime? = null,

    @LastModifiedDate
    val lastModifiedDate: LocalDateTime? = null
) {
    enum class Gender {
        MALE, FEMALE, OTHER
    }

    enum class EmploymentType {
        FULL_TIME, PART_TIME, CONTRACTOR, TEMPORARY
    }
}
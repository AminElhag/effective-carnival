package com.example.backend.mobileClient.features.employee.repository.entity

import com.example.backend.mobileClient.common.EmploymentType
import com.example.backend.mobileClient.common.Gender
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
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
    val birthdate: LocalDate? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    val gender: Gender? = null,

    @Column(name = "email", nullable = false, unique = true, length = 100)
    val email: String,

    @Column(name = "mobile_phone", length = 20)
    val mobilePhone: String? = null,

    @Column(name = "home_phone", length = 20)
    val homePhone: String? = null,

    @Column(name = "street", length = 100)
    val street: String? = null,

    @Column(name = "city", length = 50)
    val city: String? = null,

    @Column(name = "post_code", length = 20)
    val postCode: String? = null,

    @Column(name = "state", length = 50)
    val state: String? = null,

    // Employment Details
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type", length = 20)
    val employmentType: EmploymentType? = null,

    @Column(name = "employment_date")
    val employmentDate: LocalDate? = null,

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
    val username: String? = null,

    @JsonIgnore
    @Column(name = "password", length = 100)
    val password: String? = null,

    @Column(name = "profile_picture_path", length = 200)
    val profilePicturePath: String? = null,

    // Additional Settings
    @Column(name = "auto_deactivate")
    val autoDeactivate: Boolean = true,

    @Column(name = "employee_identifier", unique = true, length = 50)
    val employeeIdentifier: String? = null,

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

}
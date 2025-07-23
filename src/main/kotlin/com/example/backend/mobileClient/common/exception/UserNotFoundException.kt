package com.example.backend.mobileClient.common.exception

class UserNotFoundException(message: String = "", userId: Long = 1, username: String = "") : RuntimeException(message) {
    constructor(userId: Long) : this(message = "User with id $userId not found")
    constructor(username: String) : this(message = "User with username '$username' not found")
}


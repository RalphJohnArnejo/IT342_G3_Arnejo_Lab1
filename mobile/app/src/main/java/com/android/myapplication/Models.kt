package com.android.myapplication

// Data sent during registration and login
data class UserRequest(
    val username: String,
    val password: String
)

// Data received from the backend (if you use JWT)
data class LoginResponse(
    val token: String? = null,
    val message: String? = null
)


package com.android.myapplication

// Data sent during registration and login
data class UserRequest(
    val username: String,
    val password: String
)

// Data received from the backend during login
data class LoginResponse(
    val token: String? = null,
    val message: String? = null
)

// ADD THIS: Data received from the backend during registration
data class RegisterResponse(
    val message: String? = null
)
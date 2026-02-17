package com.android.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("api/auth/register")
    fun register(@Body user: UserRequest): Call<RegisterResponse>

    @POST("api/auth/login")
    fun login(@Body credentials: UserRequest): Call<LoginResponse>
}
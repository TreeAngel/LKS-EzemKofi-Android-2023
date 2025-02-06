package com.example.ezemkofi.util.api.retrofit

import com.example.ezemkofi.model.request.LoginRequest
import com.example.ezemkofi.model.request.RegisterRequest
import com.example.ezemkofi.model.respond.MeRespond
import retrofit2.http.*

interface RetrofitApiService {
    @POST("auth")
    suspend fun login(@Body request: LoginRequest): String

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): String

    @GET("me")
    suspend fun getProfile(@Header("Authorization") token: String): MeRespond
}
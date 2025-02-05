package com.example.ezemkofi.util.api.retrofit

import com.example.ezemkofi.model.request.LoginRequest
import com.example.ezemkofi.model.request.RegisterRequest
import com.example.ezemkofi.model.respond.AuthRespond
import com.example.ezemkofi.model.respond.MeRespond
import retrofit2.http.*

interface RetrofitApiService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthRespond

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthRespond

    @GET("auth/me")
    suspend fun getProfile(@Header("Authorization") token: String): MeRespond
}
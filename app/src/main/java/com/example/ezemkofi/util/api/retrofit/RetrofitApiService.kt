package com.example.ezemkofi.util.api.retrofit

import com.example.ezemkofi.model.request.LoginRequest
import com.example.ezemkofi.model.request.RegisterRequest
import com.example.ezemkofi.model.respond.MeRespond
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApiService {
    @POST("auth")
    suspend fun login(@Body request: LoginRequest): Response<String>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<String>

    @GET("me")
    suspend fun getProfile(@Header("Authorization") token: String): Response<MeRespond>

    @GET("me/transaction")
    suspend fun getTransaction(@Header("Authorization") token: String) // TODO: Get user all transactions
}
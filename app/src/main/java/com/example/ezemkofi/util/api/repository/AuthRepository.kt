package com.example.ezemkofi.util.api.repository

import com.example.ezemkofi.model.request.LoginRequest
import com.example.ezemkofi.model.request.RegisterRequest
import com.example.ezemkofi.model.respond.MeRespond
import com.example.ezemkofi.util.api.retrofit.RetrofitApiService

class AuthRepository(private val apiService: RetrofitApiService) {
    suspend fun login(request: LoginRequest): String {
        return apiService.login(request)
    }

    suspend fun register(request: RegisterRequest): String {
        return apiService.register(request)
    }

    suspend fun getProfile(token: String): MeRespond {
        return apiService.getProfile(token)
    }

    // TODO: Get user all transactions
}
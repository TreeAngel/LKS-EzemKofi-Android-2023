package com.example.ezemkofi.util.api.repository

import com.example.ezemkofi.model.ApiResponse
import com.example.ezemkofi.model.request.LoginRequest
import com.example.ezemkofi.model.request.RegisterRequest
import com.example.ezemkofi.model.respond.MeRespond
import com.example.ezemkofi.util.api.retrofit.RetrofitApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(private val apiService: RetrofitApiService) {
    suspend fun login(request: LoginRequest): ApiResponse<String> {
        return try {
            val response = apiService.login(request)
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiResponse.Success(it)
                } ?: ApiResponse.Error(response.message())
            } else {
                ApiResponse.Error(response.message())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ApiResponse.Error(e.message.toString())
        }
    }

    suspend fun register(request: RegisterRequest): ApiResponse<String> {
        return try {
            val response = apiService.register(request)
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiResponse.Success(it)
                } ?: ApiResponse.Error(response.message())
            } else {
                ApiResponse.Error(response.message())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ApiResponse.Error(e.message.toString())
        }
    }

    suspend fun getProfile(token: String): ApiResponse<MeRespond> {
        return try {
            val response = apiService.getProfile(token)
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiResponse.Success(it)
                } ?: ApiResponse.Error(response.message())
            } else {
                ApiResponse.Error(response.message())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ApiResponse.Error(e.message.toString())
        }
    }

//    suspend fun getTransaction(token: String) /* TODO: Add response model */ {
//        return apiService.getTransaction(token)
//    }
}
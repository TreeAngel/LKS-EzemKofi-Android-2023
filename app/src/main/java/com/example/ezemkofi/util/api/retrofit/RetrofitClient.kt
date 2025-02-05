package com.example.ezemkofi.util.api.retrofit

import android.content.Context
import com.example.ezemkofi.util.TokenManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:5000/api/"
    private var token: String? = null

    fun getInstance(context: Context): RetrofitApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(getAuthInterceptor(context))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RetrofitApiService::class.java)
    }

    private fun getAuthInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            val token = token ?: runBlocking {
                TokenManager.getTokenFlow(context).firstOrNull()?.also {
                    token = it
                }
            }
            val request = if (!token.isNullOrEmpty()) {
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                chain.request()
            }
            chain.proceed(request)
        }
    }

    fun clearToken() {
        token = null
    }
}
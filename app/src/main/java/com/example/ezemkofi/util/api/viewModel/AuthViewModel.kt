package com.example.ezemkofi.util.api.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezemkofi.model.request.LoginRequest
import com.example.ezemkofi.model.request.RegisterRequest
import com.example.ezemkofi.util.api.repository.AuthRepository
import com.example.ezemkofi.util.api.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {
    private val repository = AuthRepository(RetrofitClient.getInstance())

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            try {
                val respond = repository.login(request)
                RetrofitClient.setToken(respond)
            } catch (e: Exception) {
                e.printStackTrace()
                RetrofitClient.clearToken()
            }
        }
    }

    fun register(context: Context, request: RegisterRequest) {
        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getUserProfile(context: Context) {
        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
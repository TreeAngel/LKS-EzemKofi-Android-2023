package com.example.ezemkofi.util.api.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezemkofi.model.ApiResponse
import com.example.ezemkofi.model.request.LoginRequest
import com.example.ezemkofi.model.request.RegisterRequest
import com.example.ezemkofi.model.respond.MeRespond
import com.example.ezemkofi.util.api.repository.AuthRepository
import com.example.ezemkofi.util.api.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository(RetrofitClient.getInstance())

    val loginState = MutableLiveData<ApiResponse<String>>()
    val userProfile = MutableLiveData<ApiResponse<MeRespond>>()

    fun login(request: LoginRequest) {
        viewModelScope.launch {
            loginState.value = ApiResponse.Loading
            loginState.value = repository.login(request)
        }
    }

    fun register(context: Context, request: RegisterRequest) {
        viewModelScope.launch {
            loginState.value = ApiResponse.Loading
            loginState.value = repository.register(request)
        }
    }

    fun getUserProfile(context: Context) {
        viewModelScope.launch {
            userProfile.value = ApiResponse.Loading
            val token = RetrofitClient.getToken()
            if (token != null) {
                userProfile.value = repository.getProfile(token)
            } else {
                userProfile.value = ApiResponse.Error("Unauthorized")
            }
        }
    }
}
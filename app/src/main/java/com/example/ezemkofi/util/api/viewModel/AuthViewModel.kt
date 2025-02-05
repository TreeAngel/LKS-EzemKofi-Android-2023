package com.example.ezemkofi.util.api.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ezemkofi.model.request.LoginRequest
import com.example.ezemkofi.model.request.RegisterRequest
import com.example.ezemkofi.model.respond.MeRespond
import com.example.ezemkofi.util.TokenManager
import com.example.ezemkofi.util.api.retrofit.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _authState = MutableStateFlow<String?>(null)
    val authState: StateFlow<String?> = _authState

    private val _userProfile = MutableStateFlow<MeRespond?>(null)
    val userProfile: StateFlow<MeRespond?> = _userProfile

    fun login(context: Context, request: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getInstance(context).login(request)
                TokenManager.saveToken(context, response.token)
                _authState.value = response.token
            } catch (e: Exception) {
                e.printStackTrace()
                _authState.value = null
            }
        }
    }

    fun register(context: Context, request: RegisterRequest) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.getInstance(context).register(request)
                TokenManager.saveToken(context, response.token)
                _authState.value = response.token
            } catch (e: Exception) {
                e.printStackTrace()
                _authState.value = null
            }
        }
    }

    fun getUserProfile(context: Context) {
        viewModelScope.launch {
            try {
                val token = TokenManager.getToken(context)
                if (!token.isNullOrEmpty()) {
                    val response = RetrofitClient.getInstance(context).getProfile(token)
                    _userProfile.value = response
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _userProfile.value = null
            }
        }
    }
}
package com.example.ezemkofi.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

private val Context.datastore by preferencesDataStore(name = "auth_prefs")

object TokenManager {
    private val TOKEN_KEY = stringPreferencesKey("auth_token")

    suspend fun saveToken(context: Context, token: String) {
        context.datastore.edit { pref ->
            pref[TOKEN_KEY] = token
        }
    }

    fun getTokenFlow(context: Context): Flow<String?> {
        return context.datastore.data.map { pref ->
            pref[TOKEN_KEY]
        }
    }

    suspend fun getToken(context: Context): String? {
        return context.datastore.data.map { pref ->
            pref[TOKEN_KEY]
        }.firstOrNull()
    }

    suspend fun clearToken(context: Context) {
        context.datastore.edit { pref ->
            pref.remove(TOKEN_KEY)
        }
    }
}
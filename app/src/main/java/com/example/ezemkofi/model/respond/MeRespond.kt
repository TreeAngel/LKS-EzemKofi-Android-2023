package com.example.ezemkofi.model.respond


import com.google.gson.annotations.SerializedName

data class MeRespond(
    @SerializedName("email")
    val email: String,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("username")
    val username: String
)
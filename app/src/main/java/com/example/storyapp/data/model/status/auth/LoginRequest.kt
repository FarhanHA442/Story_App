package com.example.storyapp.data.model.status.auth

import retrofit2.http.Field

data class LoginRequest(
    @Field("email")
    val email: String,

    @Field("password")
    val password: String
)
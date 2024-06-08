package com.example.storyapp.data.model.status.auth

import retrofit2.http.Field

data class RegisterRequest(
    @Field("name")
    val name: String,

    @Field("email")
    val email: String,

    @Field("password")
    val password: String
)
package com.example.storyapp.data.network

import com.example.storyapp.data.model.status.auth.LoginRequest
import com.example.storyapp.data.model.status.auth.LoginResponse
import com.example.storyapp.data.model.status.auth.RegisterRequest
import com.example.storyapp.data.model.status.auth.RegisterResponse
import com.example.storyapp.data.model.status.story.StoryAddResponse
import com.example.storyapp.data.model.status.story.StoryAllResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface StoryService {
    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("stories")
    suspend fun getAll(): StoryAllResponse

    @Multipart
    @POST("stories")
    suspend fun upload(
        @Part
        photo: MultipartBody.Part,

        @Part("description")
        description: RequestBody
    ): StoryAddResponse
}
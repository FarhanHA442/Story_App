package com.example.storyapp.data.model.status.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse(

    @Json(name = "error")
    val error: Boolean = false,

    @Json(name = "message")
    val message: String = ""
)
package com.example.storyapp.data.repository.story

import android.net.Uri
import androidx.core.net.toFile
import com.example.storyapp.data.model.status.network.ApiStatus
import com.example.storyapp.data.model.status.story.StoryAddResponse
import com.example.storyapp.data.model.status.story.StoryAllResponse
import com.example.storyapp.data.network.StoryService
import com.example.storyapp.utils.helper.reduceFileImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

interface StoryRepository {
    fun getAllStories(): Flow<ApiStatus<StoryAllResponse>>
    fun uploadStory(
        imageUri: Uri, description: String
    ): Flow<ApiStatus<StoryAddResponse>>
}

class StoryRepositoryImpl(private val api: StoryService) : StoryRepository {
    override fun getAllStories(): Flow<ApiStatus<StoryAllResponse>> = flow {
        try {
            emit(ApiStatus.Loading)
            val response = api.getAll()
            if (!response.error) {
                emit(ApiStatus.Success(response))
            } else {
                throw Exception()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }

    override fun uploadStory(
        imageUri: Uri, description: String
    ): Flow<ApiStatus<StoryAddResponse>> = flow {
        try {
            emit(ApiStatus.Loading)

            val photo = imageUri.toFile().reduceFileImage()
            val requestImageFile = photo.asRequestBody("image/*".toMediaType())
            val descriptionBody =
                description.toRequestBody("text/plain".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "photo", photo.name, requestImageFile
            )

            val response = api.upload(multipartBody, descriptionBody)

            if (!response.error) {
                emit(ApiStatus.Success(response))
            } else {
                emit(ApiStatus.Error(response.message))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ApiStatus.Error(e.message.toString()))
        }
    }

}
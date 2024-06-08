package com.example.storyapp.appearance.fragment.story.add

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp.data.model.status.network.ApiStatus
import com.example.storyapp.data.model.status.story.StoryAddResponse
import com.example.storyapp.data.repository.story.StoryRepository
import kotlinx.coroutines.launch

class StoryAddViewModel(private val repository: StoryRepository) : ViewModel() {
    private val _uploadStoryResult =
        MutableLiveData<ApiStatus<StoryAddResponse>>()
    val uploadStoryResult: LiveData<ApiStatus<StoryAddResponse>> =
        _uploadStoryResult

    fun uploadStory(imageUri: Uri, description: String) {
        viewModelScope.launch {
            repository.uploadStory(imageUri, description).collect {
                _uploadStoryResult.value = it
            }
        }
    }
}
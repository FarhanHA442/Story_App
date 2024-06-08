package com.example.storyapp.appearance.fragment.story.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp.data.model.status.network.ApiStatus
import com.example.storyapp.data.model.status.story.StoryAllResponse
import com.example.storyapp.data.repository.story.StoryRepository
import com.example.storyapp.utils.PreferenceManager
import kotlinx.coroutines.launch

class StoryListViewModel(
    val repository: StoryRepository,
    val pref: PreferenceManager
) : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private var _storyResult = MutableLiveData<ApiStatus<StoryAllResponse>>()
    val storyResult: LiveData<ApiStatus<StoryAllResponse>> = _storyResult

    fun getCurrentUserName() {
        _username.value = pref.name
    }

    fun getAllStories() {
        viewModelScope.launch {
            repository.getAllStories().collect {
                _storyResult.value = it
            }
        }
    }

    init {
        getCurrentUserName()
    }
}
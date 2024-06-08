package com.example.storyapp.appearance.fragment.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp.data.model.status.auth.RegisterRequest
import com.example.storyapp.data.model.status.auth.RegisterResponse
import com.example.storyapp.data.model.status.network.ApiStatus
import com.example.storyapp.data.repository.user.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _registerResult = MutableLiveData<ApiStatus<RegisterResponse>>()
    val registerResult: LiveData<ApiStatus<RegisterResponse>> = _registerResult

    fun register(user: RegisterRequest) {
        viewModelScope.launch {
            repository.register(user).collect {
                _registerResult.value = it
            }
        }
    }
}
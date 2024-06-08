package com.example.storyapp.appearance.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyapp.data.model.status.auth.LoginRequest
import com.example.storyapp.data.model.status.auth.LoginResponse
import com.example.storyapp.data.model.status.network.ApiStatus
import com.example.storyapp.data.repository.user.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(val repository: AuthRepository) : ViewModel() {

    private val _loginResult = MutableLiveData<ApiStatus<LoginResponse>>()
    val loginResult: LiveData<ApiStatus<LoginResponse>> = _loginResult

    fun login(user: LoginRequest) {
        viewModelScope.launch {
            repository.login(user).collect {
                _loginResult.value = it
            }
        }
    }
}
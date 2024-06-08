package com.example.storyapp.appearance.fragment.setting

import androidx.lifecycle.ViewModel
import com.example.storyapp.data.repository.user.AuthRepository

class SettingViewModel(private val repository: AuthRepository) : ViewModel() {
    fun logout(): Boolean {
        return repository.logout()
    }
}
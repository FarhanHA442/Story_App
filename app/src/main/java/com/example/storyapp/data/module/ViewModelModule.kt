package com.example.storyapp.data.module

import com.example.storyapp.appearance.fragment.auth.AuthViewModel
import com.example.storyapp.appearance.fragment.login.LoginViewModel
import com.example.storyapp.appearance.fragment.register.RegisterViewModel
import com.example.storyapp.appearance.fragment.setting.SettingViewModel
import com.example.storyapp.appearance.fragment.story.add.StoryAddViewModel
import com.example.storyapp.appearance.fragment.story.list.StoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { StoryListViewModel(get(), get()) }
    viewModel { SettingViewModel(get()) }
    viewModel { StoryAddViewModel(get()) }
}
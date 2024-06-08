package com.example.storyapp.utils

import com.example.storyapp.R
import com.example.storyapp.data.module.networkModule
import com.example.storyapp.data.module.repositoryModule
import com.example.storyapp.data.module.viewModelModule

val koinModules = listOf(
    networkModule,
    repositoryModule,
    viewModelModule,
)

val bottomBarScope = listOf(R.id.listStoryFragment, R.id.settingsFragment)

const val BASE_URL = "https://story-api.dicoding.dev/v1/"

const val PREFS_NAME: String = "auth_pref"
const val TOKEN_KEY: String = "auth_token"
const val NAME_KEY: String = "auth_name"
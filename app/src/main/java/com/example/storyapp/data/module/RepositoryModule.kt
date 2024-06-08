package com.example.storyapp.data.module

import com.example.storyapp.data.repository.story.StoryRepository
import com.example.storyapp.data.repository.story.StoryRepositoryImpl
import com.example.storyapp.data.repository.user.AuthRepository
import com.example.storyapp.data.repository.user.AuthRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<StoryRepository> { StoryRepositoryImpl(get()) }
}
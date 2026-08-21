package com.example.jetpackcompose.di

import com.example.jetpackcompose.data.repository.AuthRepository
import com.example.jetpackcompose.domain.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton //аннотация, чтобы не пересоздавала каждый раз новый репозиторий
    fun provideAuthRepository(userDao: UserDao): AuthRepository{
        return AuthRepository(userDao)
    }

}
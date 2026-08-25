package com.example.jetpackcompose.di

import android.content.Context
import com.example.jetpackcompose.data.repository.AuthRepository
import com.example.jetpackcompose.data.repository.LocalAuthManager
import com.example.jetpackcompose.data.repository.NewsRepostory
import com.example.jetpackcompose.domain.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalAuthManager(@ApplicationContext context: Context): LocalAuthManager {
        return LocalAuthManager(context)
    }

    @Provides
    @Singleton //аннотация, чтобы не пересоздавала каждый раз новый репозиторий
    fun provideAuthRepository(userDao: UserDao, localAuthManager: LocalAuthManager): AuthRepository{
        return AuthRepository(userDao, localAuthManager)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(): NewsRepostory{
        return NewsRepostory()
    }

}
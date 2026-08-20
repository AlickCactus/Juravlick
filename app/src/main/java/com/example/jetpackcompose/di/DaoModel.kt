package com.example.jetpackcompose.di

import android.content.Context
import androidx.room.Room
import com.example.jetpackcompose.data.database.UserDatabase
import com.example.jetpackcompose.domain.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
object DaoModel {
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase{
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "userDatabase.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao{
        return userDatabase.getUserDao()
    }
}
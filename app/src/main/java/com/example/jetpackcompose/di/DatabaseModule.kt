package com.example.jetpackcompose.di

import android.content.Context
import androidx.room.Room
import com.example.jetpackcompose.data.database.UserDatabase
import com.example.jetpackcompose.domain.dao.FavoriteNewsDao
import com.example.jetpackcompose.domain.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase{
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "userDatabase.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao{
        return userDatabase.getUserDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteNewsDao(userDatabase: UserDatabase): FavoriteNewsDao {
        return userDatabase.getFavoriteNewsDao()
    }
}
package com.example.jetpackcompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpackcompose.domain.dao.UserDao
import com.example.jetpackcompose.domain.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}
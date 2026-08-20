package com.example.jetpackcompose.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey //та единица, по которой идентифицируем пользователя
    val id: String,

    @ColumnInfo(name = "username") //все колонки с названием.Room сам автоматически создает, но мы можем задать имя
    val username: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "password")
    val password: String
)

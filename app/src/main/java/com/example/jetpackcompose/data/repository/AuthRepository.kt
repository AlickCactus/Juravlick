package com.example.jetpackcompose.data.repository

import com.example.jetpackcompose.domain.dao.UserDao
import com.example.jetpackcompose.domain.entity.User
import com.example.jetpackcompose.domain.util.Result
import java.util.UUID
import javax.inject.Inject

//обязательно принимает UserDao
class AuthRepository @Inject constructor(
    private val userDao: UserDao
){
    suspend fun login(email: String, password: String): Result{
        val loginUser = userDao.login(email, password)
        val result = if(loginUser == null) Result.Failure<Unit>("Login failed")
        else Result.Success<Unit>("Login successed")

        return result
    }

    suspend fun register(username: String, email: String, password: String): Result{
        if (userDao.getUserByEmail(email) != null)
            return Result.Failure<Unit>("User with this email already exist")
        val user = User(
            id = UUID.randomUUID().toString(),
            username = username,
            email = email,
            password = password
        )
        userDao.addUser(user)
        return Result.Success<Unit>("You're registered")
    }
}
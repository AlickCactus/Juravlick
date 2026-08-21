package com.example.jetpackcompose.presentation.screen.state

import com.example.jetpackcompose.util.Result

//для событий(изменение имени/почты/пароля, переход по кнопке)
sealed class RegisterScreenEvent {
    data class UsernameUpdated(val newUsername: String): RegisterScreenEvent()
    data class EmailUpdated(val newEmail: String): RegisterScreenEvent()
    data class PasswordUpdated(val newPassword: String): RegisterScreenEvent()
    data object RegisterBtnClicked: RegisterScreenEvent()
}

data class RegisterScreenState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val registerResult: Result? = null
)
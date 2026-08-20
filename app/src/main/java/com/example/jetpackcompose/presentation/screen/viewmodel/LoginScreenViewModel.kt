package com.example.jetpackcompose.presentation.screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.presentation.screen.state.LoginScreenEvent
import com.example.jetpackcompose.presentation.screen.state.LoginScreenState

class LoginScreenViewModel : ViewModel() {
    var state by mutableStateOf(LoginScreenState())
        private set

    fun onEvent(event: LoginScreenEvent){
        when (event) {
            is LoginScreenEvent.EmailUpdated -> {this.state = state.copy(email = event.newEmail) }
            is LoginScreenEvent.PasswordUpdated -> {this.state = state.copy(password = event.newPassword)}
        }
    }

    fun updateEmail(email: String) {
        this.state = state.copy(email = email)
    }

    fun updatePassword(password: String){
        this.state = state.copy(password = password)
    }
}
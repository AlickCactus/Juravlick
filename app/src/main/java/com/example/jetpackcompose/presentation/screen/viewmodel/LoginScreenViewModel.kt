package com.example.jetpackcompose.presentation.screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.data.repository.AuthRepository
import com.example.jetpackcompose.presentation.screen.state.LoginScreenEvent
import com.example.jetpackcompose.presentation.screen.state.LoginScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    var state by mutableStateOf(LoginScreenState())
        private set

    fun onEvent(event: LoginScreenEvent){
        when (event) {
            is LoginScreenEvent.EmailUpdated -> {this.state = state.copy(email = event.newEmail) }
            is LoginScreenEvent.PasswordUpdated -> {this.state = state.copy(password = event.newPassword)}
            LoginScreenEvent.LoginBtnClicked -> login()
        }
    }

    private fun login(){
        val email = state.email
        val password = state.password
        val result = authRepository.login(email, password)
        this.state = state.copy(loginResult = result)
    }
}
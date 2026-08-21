package com.example.jetpackcompose.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcompose.R
import com.example.jetpackcompose.presentation.navigation.Screen
import com.example.jetpackcompose.presentation.screen.state.RegisterScreenEvent
import com.example.jetpackcompose.presentation.screen.state.RegisterScreenState
import com.example.jetpackcompose.presentation.screen.viewmodel.RegisterScreenViewModel
import com.example.jetpackcompose.presentation.ui.theme.components.StyleButton
import com.example.jetpackcompose.util.Result


@Composable
fun RegisterScreen(
    onNavigetTo: (Screen) -> Unit
) {
    val viewModel: RegisterScreenViewModel = hiltViewModel()

    val context = LocalContext.current
    LaunchedEffect(viewModel.state.registerResult) {
        viewModel.state.registerResult?.let { registerResult ->
            when(registerResult){
                is Result.Success<*> -> {
                    onNavigetTo(Screen.Main)
                }
                is Result.Failure<*> -> {
                    Toast.makeText(context, registerResult.msg, Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    RegisterView(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        onNavigetTo = onNavigetTo
    )
}


@Composable
fun RegisterView(
    state: RegisterScreenState = RegisterScreenState(),
    onEvent: (RegisterScreenEvent) -> Unit = {},
    onNavigetTo: (Screen) -> Unit = {}
){
    var currentColor by remember { mutableStateOf(Color.Black) }

    Column(
        modifier = Modifier.fillMaxSize().background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 50.sp,
            modifier = Modifier.padding(top = 60.dp)
        )

        OutlinedTextField(
            value = state.username,
            onValueChange = {
                onEvent(RegisterScreenEvent.UsernameUpdated(it))
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_child_care_24),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(text = stringResource(id = R.string.enter_username))
            },
            modifier = Modifier.padding(top = 150.dp),
        )

        OutlinedTextField(
            value = state.email,
            onValueChange = {
                onEvent(RegisterScreenEvent.EmailUpdated(it))
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_email_24),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(text = stringResource(id = R.string.enter_email))
            },
            modifier = Modifier.padding(top = 10.dp)
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = {
                onEvent(RegisterScreenEvent.PasswordUpdated(it))
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.outline_asterisk_24),
                    contentDescription = null
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                Text(text = stringResource(id = R.string.enter_password))
            },
            modifier = Modifier.padding(top = 10.dp)
        )

        StyleButton(onClick = {onEvent(RegisterScreenEvent.RegisterBtnClicked)}, modifier = Modifier.padding(top = 30.dp)) {
            Text(
                text = stringResource(id = R.string.login),
                fontSize = 19.sp
            )
        }

        Text(
            text = stringResource(id = R.string.have_account_login),
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 20.dp).clickable{
                currentColor = Color.Cyan
                onNavigetTo(Screen.Login)}
        )

    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview(){
    RegisterView()
}
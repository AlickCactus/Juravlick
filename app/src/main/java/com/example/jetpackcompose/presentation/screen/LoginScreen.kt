package com.example.jetpackcompose.presentation.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcompose.R
import com.example.jetpackcompose.presentation.navigation.Screen
import com.example.jetpackcompose.presentation.screen.state.LoginScreenEvent
import com.example.jetpackcompose.presentation.screen.state.LoginScreenState
import com.example.jetpackcompose.presentation.screen.viewmodel.LoginScreenViewModel
import com.example.jetpackcompose.presentation.ui.theme.components.StyleButton

@Composable
fun LoginScreen(
    onNavigateTo: (Screen) -> Unit
){
    val viewModel = viewModel<LoginScreenViewModel>()
    LoginView(
        state = viewModel.state,
        onNavigateTo = onNavigateTo,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun LoginView(
    onNavigateTo: (Screen) -> Unit = {},
    state: LoginScreenState = LoginScreenState(),
    //функция для изменения stete, т.к не могу обратиться ко ViewModel
    onEvent: (LoginScreenEvent) -> Unit = {} //по умолчанию пустой


    /*    viewModel: LoginScreenViewModel = viewModel()
    Проблемно,т.к функция не полностьб изолирована и при тестировании придется билдить ViewModel, а мы хотим отдельно протестировать
    Вместо этого можем передавать статусы. Для этог создадим класс, в котором будут разные статусы. Например по умолчанию
     */
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 50.sp,
            modifier = Modifier.padding(top = 60.dp)
        )

        OutlinedTextField( //изменяемая ячейка
            value = state.email,
            onValueChange = {
                onEvent(LoginScreenEvent.EmailUpdated(it))
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
            modifier = Modifier.padding(top = 180.dp),
        )

        OutlinedTextField( //изменяемая ячейка
            value = state.password,
            onValueChange = {
                onEvent(LoginScreenEvent.PasswordUpdated(it))
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
            modifier = Modifier.padding(10.dp)
        )

        StyleButton(onClick = {}, modifier = Modifier.padding(top = 30.dp)) {
            Text(
                text = stringResource(id = R.string.login),
                fontSize = 19.sp
            )
        }

        Text(
            text = stringResource(id = R.string.no_account_register),
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 20.dp).clickable{onNavigateTo(Screen.Register)}
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview(){
    LoginView()
}
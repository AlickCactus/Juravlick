package com.example.jetpackcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackcompose.presentation.screen.login.LoginScreen
import com.example.jetpackcompose.presentation.screen.main.MainScreen
import com.example.jetpackcompose.presentation.screen.register.RegisterScreen
import kotlinx.serialization.Serializable


/*serailization нужен для преобразования data object в строки.
По умолчанию в jetpack compose distination(все экраны и пути) прописаны, как строки
 */

sealed class Screen {
    @Serializable
    data object Login: Screen() //наследуется от Screen

    @Serializable
    data object Register: Screen()

    @Serializable
    data object Main: Screen()
}

@Composable
fun MainNav(
    modifier: Modifier = Modifier, //дополнительные параметры
    navHostController: NavHostController, //контроль с экранов, за подключение, за хранение и тп
    isLoggedIn: Boolean
){
    NavHost( //composable функция
        modifier = modifier,
        navController = navHostController,
        startDestination = if (isLoggedIn) Screen.Main else Screen.Login
    ) {
        //все действия по перехода выполняются тут, а не loginScreen, чтобы не было проблем, н-р утечек памяти
        //создавать ViewModel и собирать с нее state
        composable<Screen.Login> {
            LoginScreen (
                onNavigateTo = { navigateTo ->
                    navHostController.navigate(navigateTo)
                }
            )
        }

        composable<Screen.Register> {
            RegisterScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }

        composable<Screen.Main> {
            MainScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
    }
}
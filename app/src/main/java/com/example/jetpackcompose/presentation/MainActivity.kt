package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcompose.presentation.navigation.MainNav
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier
){
    MainNav(navHostController = rememberNavController(), modifier = modifier) //возвращает navHostController, который контроллирует все экраны и перемещение между ними. Он будет запоминать так, чтобы не появлялся новый navController
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeTheme {
        MainContent()
    }
}

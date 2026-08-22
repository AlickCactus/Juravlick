package com.example.jetpackcompose.domain.util

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackcompose.presentation.screen.main.navigation.MainScreenNavigationRoute

data class BottomNavItem (
    val icon: ImageVector,
    val titleResId: Int,
    val route: MainScreenNavigationRoute
)
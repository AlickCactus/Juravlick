package com.example.jetpackcompose.presentation.screen.main.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.domain.model.NewsItem
import kotlinx.datetime.LocalDateTime

@Composable
fun FeedScreen(){
    var searchText by remember { mutableStateOf("") }
    val sampleNewsItem = listOf(
        NewsItem(
            id = "1",
            title = "Google представила новую версию Android Studio",
            description = "Обновление добавляет улучшенную поддержку Compose, более быструю сборку и новые инструменты для отладки UI.",
            publishedBy = "Android Developers",
            publishedAt = LocalDateTime(2026, 8, 20, 14, 30),
            imageUrl = "",
            isFavorite = false
        ),

        NewsItem(
            id = "2",
            title = "Jetpack Compose 1.9 вышел в stable",
            description = "В релизе улучшена производительность, добавлены новые API для анимаций и исправлены ошибки Material 3.",
            publishedBy = "Jetpack News",
            publishedAt = LocalDateTime(2026, 8, 21, 9, 15),
            imageUrl = "",
            isFavorite = true
    ),

        NewsItem(
            id = "3",
            title = "Kotlin 2.1: что нового для Android-разработчиков",
            description = "Компилятор стал быстрее, улучшена поддержка K2, а также добавлены новые возможности для coroutines и serialization.",
            publishedBy = "Kotlin Blog",
            publishedAt = LocalDateTime(2026, 8, 22, 18, 45),
            imageUrl = "",
            isFavorite = false
        )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = searchText,
            onValueChange = {searchText = it},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Иконка поиска",
                    tint = Color.Gray
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_through_news),
                    fontSize = 18.sp
                )
            }
        )

        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn (
            modifier = Modifier.fillMaxSize(0.9f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            items(sampleNewsItem){
                com.example.jetpackcompose.presentation.ui.components.NewsItem(
                    modifier = Modifier.padding(top = 10.dp),
                    newsItem = it,
                    onFavoriteClicked = {},
                    onReadClicked = {}
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun FeedScreenPreview(){
    FeedScreen()
}
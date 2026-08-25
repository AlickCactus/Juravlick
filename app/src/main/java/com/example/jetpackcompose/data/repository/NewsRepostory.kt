package com.example.jetpackcompose.data.repository

import com.example.jetpackcompose.domain.model.NewsItem
import kotlinx.datetime.LocalDateTime

class NewsRepostory {
    //модфикатор. коорый объявляет функцию, способную приостанавливать свое выполнение без блокировки потока и позже возобновиться
    suspend fun loadNews() : List<NewsItem>{
        return listOf(
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
    }
}
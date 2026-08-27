package com.example.jetpackcompose.data.util

import com.example.jetpackcompose.data.dto.NewsItemDto
import com.example.jetpackcompose.domain.model.NewsItem
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.security.MessageDigest

fun NewsItemDto.toModel(isFavorite: Boolean = false): NewsItem {
    return NewsItem(
        id = generateNewsItemIdFromUrl(url),
        title = title ?: "No Title",
        url = url.orEmpty(),
        description = description ?: "No Description",
        publishedBy = source?.name ?: "Unknown Source",
        publishedAt = publishedAt
            ?.let { Instant.parse(it).toLocalDateTime(TimeZone.currentSystemDefault()) }
            ?: Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        imageUrl = urlToImage.orEmpty(),
        isFavorite = isFavorite
    )
}

fun generateNewsItemIdFromUrl(url: String?): String {
    return if (url != null) {
        MessageDigest.getInstance("MD5")
            .digest(url.toByteArray())
            .joinToString("") { "%02x".format(it) }
    } else {
        System.currentTimeMillis().toString()
    }
}

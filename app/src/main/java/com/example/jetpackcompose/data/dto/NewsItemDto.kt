package com.example.jetpackcompose.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsItemDto(
    val title: String? = null,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    @SerialName("publishedAt")
    val publishedAt: String? = null,
    val source: SourceDto? = null
)

@Serializable
data class SourceDto(
    val id: String? = null,
    val name: String? = null
)

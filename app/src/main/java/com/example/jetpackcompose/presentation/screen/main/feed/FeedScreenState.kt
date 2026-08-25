package com.example.jetpackcompose.presentation.screen.main.feed

import com.example.jetpackcompose.domain.model.NewsItem

data class FeedScreenState(
    val searchQuery: String = "",
    val filteredNews: List<NewsItem> = emptyList()
)

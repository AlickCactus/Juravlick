package com.example.jetpackcompose.presentation.screen.main.feed

import com.example.jetpackcompose.domain.model.NewsItem


sealed interface FeedScreenEvent {
    data class SearchQueryChanged(val newsSearchQuery: String): FeedScreenEvent
    data class NewsItemClicked(val newsItem: NewsItem) : FeedScreenEvent
    data class NewsItemFavoriteToggleClicked(val newsItem: NewsItem): FeedScreenEvent
}
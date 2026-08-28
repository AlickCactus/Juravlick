package com.example.jetpackcompose.presentation.screen.main.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.data.repository.NewsRepository
import com.example.jetpackcompose.domain.model.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class FeedScreenViewModel @Inject constructor(
    private val newsRepostory: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FeedScreenState())
    val state = _state.asStateFlow()

    private var news: List<NewsItem> = emptyList()

    init {
        loadNews()
    }

    fun onEvent(event: FeedScreenEvent) {
        when (event) {
            is FeedScreenEvent.NewsItemClicked -> onNewsItemClicked(event.newsItem)
            is FeedScreenEvent.SearchQueryChanged -> onSearchQueryChanged(event.newsSearchQuery)
            is FeedScreenEvent.NewsItemFavoriteToggleClicked -> onNewsItemFavoriteToggleClicked(event.newsItem)
        }
    }

    private fun onNewsItemClicked(newsItem: NewsItem){
        _state.update { it.copy(selectedNewsArtcleUrl = newsItem.url) }
    }

    private fun onNewsItemFavoriteToggleClicked(newsItem: NewsItem) {
        news = news.map {
            if (it.id == newsItem.id) it.copy(isFavorite = !it.isFavorite)
            else it
        }
        viewModelScope.launch {
            _state.update {
                it.copy(filteredNews = filterNews(it.searchQuery, news))
            }
        }
    }

    private fun onSearchQueryChanged(newQuery: String) {
        _state.update { it.copy(searchQuery = newQuery) }

        viewModelScope.launch {
            _state.update {
                it.copy(filteredNews = filterNews(newQuery, news))
            }
        }
    }

    private fun loadNews() = viewModelScope.launch {
        val loadedNews = withContext(Dispatchers.IO) {
            newsRepostory.loadNews()
        }
        this@FeedScreenViewModel.news = loadedNews
        _state.update {
            it.copy(filteredNews = filterNews(it.searchQuery, loadedNews))
        }
    }

    private suspend fun filterNews(query: String, news: List<NewsItem>): List<NewsItem> {
        return withContext(Dispatchers.Default) {
            if (query.isEmpty()) news
            else news.filter { it.title.contains(query, ignoreCase = true) }
        }
    }
}

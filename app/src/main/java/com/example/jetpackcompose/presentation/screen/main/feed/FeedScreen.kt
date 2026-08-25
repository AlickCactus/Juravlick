package com.example.jetpackcompose.presentation.screen.main.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcompose.R
import com.example.jetpackcompose.presentation.navigation.Screen
import com.example.jetpackcompose.presentation.ui.components.NewsItem

@Composable
fun FeedScreen(
    navigate: (Screen) -> Unit = {}
) {
    val viewModel = hiltViewModel<FeedScreenViewModel, FeedScreenViewModel.Factory> { factory ->
        factory.create(navigate)
    }
    val state by viewModel.state.collectAsState()

    FeedScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun FeedScreenContent(
    state: FeedScreenState,
    onEvent: (FeedScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { onEvent(FeedScreenEvent.SearchQueryChanged(it)) },
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

        LazyColumn(
            modifier = Modifier.fillMaxSize(0.9f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = state.filteredNews,
                key = { it.id }
            ) { newsItem ->
                NewsItem(
                    newsItem = newsItem,
                    onFavoriteClicked = {
                        onEvent(FeedScreenEvent.NewsItemFavoriteToggleClicked(newsItem))
                    },
                    onReadClicked = {
                        onEvent(FeedScreenEvent.NewsItemClicked(newsItem))
                    }
                )
            }
        }
    }
}

package com.example.jetpackcompose.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.jetpackcompose.R
import com.example.jetpackcompose.domain.model.NewsItem
import com.example.jetpackcompose.presentation.ui.theme.components.StyleButton
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    newsItem: NewsItem,
    onFavoriteClicked: () -> Unit, //сюда надо передать функцию, которая ничего не принимает и ничего не возвращает
    onReadClicked: () -> Unit
){
    Card(
        modifier = modifier.fillMaxSize().background(
            Color.White),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp) //тень
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                model = newsItem.imageUrl,
                contentDescription = "Фото новости",
                modifier = Modifier.fillMaxSize().height(200.dp).clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = newsItem.title,
                    fontSize = 22.sp
                )
                IconButton(
                    onClick = onFavoriteClicked
                ){
                    Icon(
                        imageVector = if (newsItem.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                        contentDescription = "Добавь в избранное"
                    )
                }
            }

            Text(
                text = newsItem.description,
                maxLines = 3,
                fontSize = 18.sp
            )
            StyleButton(
                onClick = onReadClicked
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Text(
                        text = stringResource(R.string.read)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Icon(
                        imageVector = Icons.Outlined.ArrowUpward,
                        contentDescription = "Стрелка",
                        modifier = Modifier.rotate(90f)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NewsItemPreview(){
    JetpackComposeTheme {
        NewsItem(
            newsItem = com.example.jetpackcompose.domain.model.NewsItem(
                id = "1",
                title = "News item 1",
                description = "News item 1 decription",
                url = "",
                publishedBy = "News source",
                publishedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                imageUrl = "",
                isFavorite = true
            ),
            onFavoriteClicked = {},
            onReadClicked = {}
        )

    }
}
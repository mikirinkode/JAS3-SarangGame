package com.mikirinkode.saranggame.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mikirinkode.saranggame.ui.components.GameCompactCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    onBackClicked: () -> Unit,
    onGameClicked: (gameId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackClicked() // TODO: CHECK
    }

    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = { Text(text = "Game List") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
            navigationIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(imageVector = Icons.Sharp.ArrowBack, contentDescription = "Back Icon")
                }
            }
        )
        GameList(list = listOf("", "", "", ""), onGameClicked = onGameClicked)
    }
}

@Composable
fun GameList(
    list: List<String>,
    onGameClicked: (gameId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier,
    ) {
        items(list) { genre ->
            GameCompactCard(
                imageUrl = "https://media.rawg.io/media/games/26d/26d4437715bee60138dab4a7c8c59c92.jpg",
                title = "Cyberpunk",
                rating = "8.3",
                genres = "Action, Adventure",
                onItemClick = {
                    onGameClicked("1") // TODO
                }
            )
        }
    }
}
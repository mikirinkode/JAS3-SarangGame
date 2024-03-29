package com.mikirinkode.saranggame.ui.screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mikirinkode.saranggame.R
import com.mikirinkode.saranggame.data.response.Game
import com.mikirinkode.saranggame.ui.components.GameCompactCard
import com.mikirinkode.saranggame.ui.components.GameGrid
import com.mikirinkode.saranggame.ui.components.LoadingIndicator
import com.mikirinkode.saranggame.utils.ContentLayoutType
import com.mikirinkode.saranggame.utils.UiState
import com.mikirinkode.saranggame.utils.Utils
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    contentLayoutType: ContentLayoutType,
    genreId: Int,
    viewModel: GameViewModel,
    onBackClicked: () -> Unit,
    onGameClicked: (gameId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackClicked() // TODO: CHECK
    }

    Log.e("GameListScreen", "genre id: $genreId")

    Column(
        modifier = modifier
    ) {
        if (contentLayoutType == ContentLayoutType.LIST_ONLY) {
            TopAppBar(
                title = { Text(text = "Game List") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(imageVector = Icons.Sharp.ArrowBack, contentDescription = "Back Icon")
                    }
                }
            )
        } else {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dice),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(text = " SarangGame")
                    }
                })
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            viewModel.gameListState.collectAsState().value.let { state ->
                when (state) {
                    is UiState.Loading -> {
                        if (genreId != -1) {
                            viewModel.getGameListByGenreId(genreId)
                        }
                        LoadingIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    is UiState.Error -> {} // TODO
                    is UiState.Success -> {
                        val list = state.data

                        if (contentLayoutType == ContentLayoutType.LIST_ONLY) {
                            GameList(list = list, onGameClicked = onGameClicked)
                        } else {
                            // use GRID
                            GameGrid(list = list, onGameClicked = onGameClicked)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GameList(
    list: List<Game>,
    onGameClicked: (gameId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 16.dp),
        modifier = modifier,
    ) {
        items(list) { game ->
            val genres = Utils.getGenres(game.genres)
            val rating = DecimalFormat("#.#").format(game.rating?.times(2))

            GameCompactCard(
                imageUrl = game.backgroundImage ?: "",
                title = game.name ?: "",
                rating = rating,
                genres = genres,
                onItemClick = {
                    onGameClicked(game.id ?: 1) // TODO
                }
            )
        }
    }
}
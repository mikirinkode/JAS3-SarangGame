package com.mikirinkode.saranggame.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mikirinkode.saranggame.data.response.Game
import com.mikirinkode.saranggame.utils.Utils
import java.text.DecimalFormat

@Composable
fun GameGrid(
    list: List<Game>,
    onGameClicked: (gameId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(bottom = 16.dp),
        columns = GridCells.Adaptive(200.dp),
        modifier = modifier,
//        modifier = modifier,
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
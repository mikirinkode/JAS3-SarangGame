package com.mikirinkode.saranggame.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mikirinkode.saranggame.utils.ContentLayoutType

@Composable
fun HomeScreen(
    contentLayoutType: ContentLayoutType,
    viewModel: GameViewModel,
    modifier: Modifier = Modifier
) {

    when (contentLayoutType) {
        ContentLayoutType.LIST_ONLY -> {
            when (viewModel.currentScreen) {
                GameScreenType.GENRE_LIST -> {
                    GenreListScreen(
                        genreId = viewModel.genreId,
                        contentLayoutType = contentLayoutType,
                        viewModel = viewModel,
                        onGenreClicked = viewModel::onGenreClicked,
                    )
                }

                GameScreenType.GAME_LIST -> {
                    GameListScreen(
                        contentLayoutType = contentLayoutType,
                        genreId = viewModel.genreId,
                        viewModel = viewModel,
                        onBackClicked = viewModel::onBackClicked,
                        onGameClicked = viewModel::onGameClicked,
                    )
                }

                GameScreenType.GAME_DETAIL -> {
                    GameDetailScreen(
                        contentLayoutType = contentLayoutType,
                        viewModel = viewModel,
                        gameId = viewModel.gameId,
                        onShareClick = {},
                        openWebsite = {},
                        navigateBack = viewModel::onBackClicked,
                    )
                }
            }
        }

        ContentLayoutType.LIST_AND_DETAIL -> {
            Row(
                modifier = modifier
            ) {
                GenreListScreen(
                    genreId = viewModel.genreId,
                    contentLayoutType = contentLayoutType,
                    viewModel = viewModel,
                    onGenreClicked = viewModel::onGenreClicked,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier.weight(4f)
                ) {
                    if (viewModel.isShowGameDetail) {
                        GameDetailScreen(
                            contentLayoutType = contentLayoutType,
                            viewModel = viewModel,
                            gameId = viewModel.gameId,
                            onShareClick = {},
                            openWebsite = {},
                            navigateBack = { /*TODO*/ },
                        )
                    } else {
                        GameListScreen(
                            contentLayoutType = contentLayoutType,
                            genreId = viewModel.genreId,
                            viewModel = viewModel,
                            onBackClicked = { /*TODO*/ },
                            onGameClicked = viewModel::onGameClicked,
                        )
                    }
                }
            }
        }
    }
}
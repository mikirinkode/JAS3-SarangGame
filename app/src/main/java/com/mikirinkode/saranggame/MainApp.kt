package com.mikirinkode.saranggame

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mikirinkode.saranggame.ui.navigation.Screen
import com.mikirinkode.saranggame.ui.screen.GameDetailScreen
import com.mikirinkode.saranggame.ui.screen.GameListScreen
import com.mikirinkode.saranggame.ui.screen.GameViewModel
import com.mikirinkode.saranggame.ui.screen.GenreListScreen

@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navController?.currentDestination?.route
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)

    NavHost(
        navController = navController,
        startDestination = Screen.GenreList.route
    ) {
        composable(Screen.GenreList.route) {
            GenreListScreen(
                viewModel = viewModel,
                onGenreClicked = { genreId ->
                    navController.navigate(Screen.GameList.createRoute(genreId = genreId))
                }
            )
        }

        composable(
            route = Screen.GameList.route,
            arguments = listOf(navArgument("genreId") { type = NavType.IntType })
        ) { entry ->
            val genreId = entry.arguments?.getInt("genreId") ?: 1
            GameListScreen(
                viewModel = viewModel,
                genreId = genreId,
                onBackClicked = { navController.navigateUp() },
                onGameClicked = { gameId ->
                    navController.navigate(
                        Screen.GameDetail.createRoute(
                            gameId = gameId
                        )
                    )
                })
        }

        composable(
            route = Screen.GameDetail.route,
            arguments = listOf(navArgument("gameId") { type = NavType.IntType })
        ) { entry ->
            val gameId = entry.arguments?.getInt("gameId") ?: 1
            GameDetailScreen(
                gameId = gameId,
                onShareClick = {},
                openWebsite = {},
                navigateBack = {
                    navController.navigateUp()
                })
        }
    }
}
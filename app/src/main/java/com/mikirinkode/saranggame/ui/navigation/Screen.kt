package com.mikirinkode.saranggame.ui.navigation


sealed class Screen(val route: String) {
    object GenreList: Screen("genre")
    object GameList: Screen("game/{genreId}"){
        fun createRoute(genreId: Int) = "game/$genreId"
    }
    object GameDetail: Screen("game/{gameId}/detail") {
        fun createRoute(gameId: Int) = "game/$gameId/detail"
    }
}
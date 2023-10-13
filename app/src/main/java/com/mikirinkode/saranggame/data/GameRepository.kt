package com.mikirinkode.saranggame.data

import com.mikirinkode.saranggame.data.response.Game
import com.mikirinkode.saranggame.data.response.Genre
import com.mikirinkode.saranggame.utils.Constants

interface RepositoryInterface {
    suspend fun getGenreList(): List<Genre>
    suspend fun getGameList(genreId: String): List<Game>
    suspend fun getGameDetail(gameId: String): Game
}

class GameRepository(
    private val apiService: GameApiService,
) : RepositoryInterface {
    private val apiKey: String = Constants.API_KEY
    override suspend fun getGenreList(): List<Genre> {
        return apiService.getGenreList(apiKey).results
    }

    override suspend fun getGameList(genreId: String): List<Game> {
        return apiService.getGameListByGenre(genreId = genreId, apiKey = apiKey).results
    }

    override suspend fun getGameDetail(gameId: String): Game {
        return apiService.getGameDetail(gameId = gameId, apiKey = apiKey)
    }

}
package com.mikirinkode.saranggame.data

import com.mikirinkode.saranggame.data.response.Game
import com.mikirinkode.saranggame.data.response.GameList
import com.mikirinkode.saranggame.data.response.GenreList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GameApiService {
    @GET("genres")
    suspend fun getGenreList(
        @Query("key") apiKey: String
    ): GenreList
    @GET("games")
    suspend fun getGameListByGenre(
        @Query("genres") genreId: String,
        @Query("key") apiKey: String
    ): GameList

    @GET("games/{gameId}")
    suspend fun getGameDetail(
        @Path("gameId") gameId: String,
        @Query("key") apiKey: String
    ): Game
}
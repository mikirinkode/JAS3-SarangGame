package com.mikirinkode.saranggame.data.response

import com.google.gson.annotations.SerializedName

data class GameList(
    @field:SerializedName("results")
    val results: List<Game>,
)
package com.mikirinkode.saranggame.data

import com.google.gson.annotations.SerializedName

data class GenreModel(

    @field:SerializedName("games_count")
    val gamesCount: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("image_background")
    val imageBackground: String? = null,

    @field:SerializedName("slug")
    val slug: String? = null
)
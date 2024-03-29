package com.mikirinkode.saranggame.data.response

import com.google.gson.annotations.SerializedName

data class GenreList(
	@field:SerializedName("results")
	val results: List<Genre>
)

data class Genre(
	@field:SerializedName("games_count")
	val gamesCount: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("image_background")
	val imageBackground: String? = null,

)

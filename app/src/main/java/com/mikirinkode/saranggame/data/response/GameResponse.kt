package com.mikirinkode.saranggame.data.response

import com.google.gson.annotations.SerializedName

data class Game(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("name_original")
	val nameOriginal: String? = null,

	@field:SerializedName("updated")
	val updated: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("released")
	val released: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("playtime")
	val playtime: Int? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem>? = null,

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String? = null,

	@field:SerializedName("background_image_additional")
	val backgroundImageAdditional: String? = null,

	@field:SerializedName("saturated_color")
	val saturatedColor: String? = null,

	@field:SerializedName("dominant_color")
	val dominantColor: String? = null,
)

data class GenresItem(

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
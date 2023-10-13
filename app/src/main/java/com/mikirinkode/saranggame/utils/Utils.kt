package com.mikirinkode.saranggame.utils

import com.mikirinkode.saranggame.data.response.Genre

object Utils {
    fun formatNumberToK(number: Int): String {
        return when {
            number < 1000 -> number.toString()
            number < 1000000 -> "${number / 1000}k"
            else -> "${number / 1000000}M"
        }
    }

    fun getGenres(genres: List<Genre?>?): String {
        val result: String
        val genreList = ArrayList<String>()
        genres?.forEach { genreList.add(it?.name ?: "") }
        result = genreList.joinToString(separator = ", ")
        return result
    }
}
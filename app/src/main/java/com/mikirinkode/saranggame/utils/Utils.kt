package com.mikirinkode.saranggame.utils

import com.mikirinkode.saranggame.data.response.Genre
import java.text.SimpleDateFormat
import java.util.Locale

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

    fun formatReleaseDate(releaseDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())

        val date = inputFormat.parse(releaseDate)
        return outputFormat.format(date)
    }
}
package com.mikirinkode.saranggame.utils

object Utils {
    fun formatNumberToK(number: Int): String {
        return when {
            number < 1000 -> number.toString()
            number < 1000000 -> "${number / 1000}k"
            else -> "${number / 1000000}M"
        }
    }
}
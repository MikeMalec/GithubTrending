package com.example.business.domain

sealed class Period(val value: String) {
    object Daily : Period("daily")
    object Weekly : Period("weekly")
    object Monthly : Period("monthly")
    companion object {
        fun getByValue(value: String): Period {
            return when (value) {
                "daily" -> Period.Daily
                "weekly" -> Period.Weekly
                else -> Period.Monthly
            }
        }
    }
}
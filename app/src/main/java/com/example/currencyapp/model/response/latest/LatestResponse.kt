package com.example.currencyapp.model.response.latest

data class LatestResponse(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)
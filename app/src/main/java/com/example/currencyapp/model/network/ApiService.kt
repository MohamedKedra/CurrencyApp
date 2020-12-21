package com.example.currencyapp.model.network

import com.example.currencyapp.model.response.currency.CurrencyResponse
import com.example.currencyapp.model.response.latest.LatestResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("symbols")
    fun getAllSymbols(@Query("access_key") key: String) : Call<CurrencyResponse>

    @GET("latest")
    fun getLatestPrice(@Query("access_key") key: String, @Query("base") defaultCur: String) : Call<LatestResponse>
}
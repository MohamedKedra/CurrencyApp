package com.example.currencyapp.ui.home.repository

import com.example.currencyapp.app.base.BaseRepository
import com.example.currencyapp.model.network.ApiService
import com.example.currencyapp.model.response.currency.CurrencyResponse
import com.example.currencyapp.model.response.latest.LatestResponse
import com.example.currencyapp.utils.Constants
import io.reactivex.Single

class HomeRepository(apiService: ApiService) : BaseRepository(apiService) {

    fun getAllCurrencies(): Single<CurrencyResponse> =
        apiService.getAllSymbols(Constants.API.API_Key)

    fun getLatestPrice(): Single<LatestResponse> =
        apiService.getLatestPrice(Constants.API.API_Key)
}
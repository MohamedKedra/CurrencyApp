package com.example.currencyapp.ui.home.repository

import androidx.lifecycle.MutableLiveData
import com.example.currencyapp.app.base.BaseRepository
import com.example.currencyapp.model.network.ApiService
import com.example.currencyapp.model.response.currency.CurrencyResponse
import com.example.currencyapp.model.response.currency.Symbols
import com.example.currencyapp.model.response.latest.LatestResponse
import com.example.currencyapp.model.response.latest.Rates
import com.example.currencyapp.utils.Constants
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(apiService: ApiService) : BaseRepository(apiService) {

    private val allList = MutableLiveData<Rates>()

    fun getAllCurrencies(): Single<CurrencyResponse> =
        apiService.getAllSymbols(Constants.API.API_Key)


    fun getListCurrencies(base: String): MutableLiveData<Rates>? {
        apiService.getLatestPrice(Constants.API.API_Key, base)
            .enqueue(object : Callback<LatestResponse> {
                override fun onResponse(
                    call: Call<LatestResponse>,
                    response: Response<LatestResponse>
                ) {
                    if (response.isSuccessful) {
                        allList.value = response.body()?.rates!!
                    } else {
                        allList.value = null
                    }
                }

                override fun onFailure(call: Call<LatestResponse>, t: Throwable) {
                    allList.value = null
                }

            })
        return allList
    }
}
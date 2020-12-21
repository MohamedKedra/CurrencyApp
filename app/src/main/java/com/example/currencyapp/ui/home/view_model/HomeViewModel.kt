package com.example.currencyapp.ui.home.view_model

import android.content.Context
import android.net.ConnectivityManager
import com.example.currencyapp.app.base.BaseViewModel
import com.example.currencyapp.app.base.LiveDataState
import com.example.currencyapp.model.response.Currency
import com.example.currencyapp.model.response.CurrencyDataList
import com.example.currencyapp.model.response.currency.CurrencyResponse
import com.example.currencyapp.model.response.latest.LatestResponse
import com.example.currencyapp.ui.home.repository.HomeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val context: Context,
    private val repository: HomeRepository,
    connectivityManager: ConnectivityManager
) : BaseViewModel(connectivityManager) {

    private var currenciesListData = LiveDataState<ArrayList<Currency>>()
    private var pricesListData = LiveDataState<ArrayList<Currency>>()
    private val disposable = CompositeDisposable()
    private val disposablePrice = CompositeDisposable()

    companion object {
        private val initCurrencyList = CurrencyDataList.getInitList()
    }

    fun refreshCurrenciesList(): LiveDataState<ArrayList<Currency>> {

        if (!isNetworkAvailable) {
            publishNoInternet(currenciesListData)
            return currenciesListData
        }

        publishLoading(currenciesListData)

        disposable.add(
            repository.getAllCurrencies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                    object : DisposableSingleObserver<CurrencyResponse>() {
                        override fun onSuccess(response: CurrencyResponse) {
                            for (i in 0 until initCurrencyList.size) {
                                val item = initCurrencyList[i]
                                when (i) {
                                    0 -> item.name = response.symbols.EGP
                                    1 -> item.name = response.symbols.EUR
                                    2 -> item.name = response.symbols.GBP
                                    3 -> item.name = response.symbols.USD
                                }
                                initCurrencyList[i] = item
                            }
                            print(response)
                            publishResult(currenciesListData, initCurrencyList)
                        }

                        override fun onError(error: Throwable) {
                            publishError(currenciesListData, error)
                        }
                    }
                ))

        return currenciesListData
    }

    fun refreshHomeList(currencyName: String): LiveDataState<ArrayList<Currency>> {

        if (!isNetworkAvailable) {
            publishNoInternet(pricesListData)
            return pricesListData
        }

        publishLoading(pricesListData)

        if (initCurrencyList[0].price == 0.0) {
            disposablePrice.add(
                repository.getLatestPrice().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(
                        object : DisposableSingleObserver<LatestResponse>() {
                            override fun onSuccess(response: LatestResponse) {
                                for (i in 0 until initCurrencyList.size) {
                                    val item = initCurrencyList[i]
                                    when (i) {
                                        0 -> item.price = response.rates.EGP
                                        1 -> item.price = response.rates.EUR
                                        2 -> item.price = response.rates.GBP
                                        3 -> item.price = response.rates.USD
                                    }
                                    initCurrencyList[i] = item
                                }
                                print(response)
                                val list = ArrayList<Currency>()
                                initCurrencyList.forEach { list.add(it) }
                                publishResult(
                                    pricesListData, getCurrencyList(currencyName, list)
                                )
                            }

                            override fun onError(error: Throwable) {
                                publishError(pricesListData, error)
                            }
                        }
                    ))
        } else {
            val list = ArrayList<Currency>()
            initCurrencyList.forEach { list.add(it) }
            publishResult(
                pricesListData, getCurrencyList(currencyName, list)
            )
        }

        return pricesListData
    }

    private fun getCurrencyList(
        currencyName: String,
        myList: ArrayList<Currency>
    ): ArrayList<Currency> {
        if (!myList.isNullOrEmpty()) {
            val selectedCurrency = myList.find { it.name == currencyName }

            var cachedPrice = 0.0

            if (selectedCurrency != null)

            return if (selectedCurrency.price == 1.0) {
                myList
            } else {
                for (i in 0 until myList.size) {
                    val item = myList[i]
                    if (item.symbol == selectedCurrency.symbol) {
                        cachedPrice = selectedCurrency.price
                        myList[i].price = 1.0
                    } else {
                        myList[i].price = item.price / cachedPrice
                    }
                }
                myList
            }
        }
        return myList
    }
}
package com.example.currencyapp.ui.home.view_model

import android.content.Context
import android.net.ConnectivityManager
import com.example.currencyapp.R
import com.example.currencyapp.app.base.BaseViewModel
import com.example.currencyapp.app.base.LiveDataState
import com.example.currencyapp.model.response.currency.CurrencyResponse
import com.example.currencyapp.model.response.currency.Symbols
import com.example.currencyapp.model.response.latest.Rates
import com.example.currencyapp.ui.home.repository.HomeRepository
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val context: Context,
    private val repository: HomeRepository,
    connectivityManager: ConnectivityManager
) : BaseViewModel(connectivityManager) {

    private val currenciesListData = LiveDataState<Symbols>()
    private val disposable = CompositeDisposable()
//    private val liveDataState = LiveDataState<Rates>()

    fun refreshCurrenciesList(): LiveDataState<Symbols> {

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
                            print(response)
                            publishResult(currenciesListData, response.symbols)
                        }

                        override fun onError(error: Throwable) {
                            publishError(currenciesListData, error)
                        }
                    }
                ))

        return currenciesListData
    }
//
//    fun refreshHomeList(base :String): LiveDataState<Rates> {
//
//        if (!isNetworkAvailable) {
//            publishNoInternet(liveDataState)
//            return liveDataState
//        }
//
//        if (repository.getAllCurrencies()?.value != null) {
//            publishResult(liveDataState, repository.getListCurrencies(base)?.value!!)
//        } else {
//            publishError(liveDataState, Throwable(context.resources.getString(R.string.error)))
//        }
//
//        publishLoading(liveDataState)
//
//        return liveDataState
//    }
}
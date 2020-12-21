package com.example.currencyapp.ui.home.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.currencyapp.R
import com.example.currencyapp.app.base.BaseFragment
import com.example.currencyapp.app.base.DataState
import com.example.currencyapp.model.response.currency.Symbols
import com.example.currencyapp.ui.home.view_model.HomeViewModel
import kotlinx.android.synthetic.main.header_currency_layout.*
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), OnItemClickedListener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.home_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAllCurrency()
//        rv_currency_list.adapter = CurrencyAdapter(this)
    }

    private fun initDropdownList(symbols: Symbols) {
        var list = ArrayList<String>()
        list.add(symbols.EGP)
        list.add(symbols.EUR)
        list.add(symbols.GBP)
        list.add(symbols.USD)
//        list = list.sorted() as ArrayList<String>
        val adapter =
            ArrayAdapter(activity?.applicationContext!!, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_currencies.adapter = adapter
    }

    private fun observeAllCurrency() {

        viewModel.refreshCurrenciesList().observe(viewLifecycleOwner, {
            when (it.getStatus()) {

                DataState.DataStatus.LOADING -> {
                    showLayoutLoading(true)
                }

                DataState.DataStatus.SUCCESS -> {
                    hideLayoutErrorAndLoading()
                    it?.getData()?.let { res ->
                        initDropdownList(res)
                    } ?: run {
                        showLayoutError(true, resources.getString(R.string.error))
                    }
                }

                DataState.DataStatus.ERROR -> {
                    it?.getError()?.localizedMessage?.toString()?.let { err ->
                        showLayoutError(
                            true,
                            err
                        )
                    }
                }

                DataState.DataStatus.NO_INTERNET -> {
                    it?.getNoInternet()?.let {
                        showLayoutError(true, resources.getString(R.string.no_internet))
                    }
                }
            }
        })
    }

    override fun onItemClicked(view: View) {

    }
}
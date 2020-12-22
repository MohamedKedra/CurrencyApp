package com.example.currencyapp.ui.home.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.example.currencyapp.R
import com.example.currencyapp.app.base.BaseFragment
import com.example.currencyapp.app.base.DataState
import com.example.currencyapp.model.response.Currency
import com.example.currencyapp.model.response.currency.Symbols
import com.example.currencyapp.ui.home.view_model.HomeViewModel
import com.example.currencyapp.utils.Constants
import kotlinx.android.synthetic.main.header_currency_layout.*
import kotlinx.android.synthetic.main.home_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), OnItemClickedListener, View.OnClickListener {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var adapter: CurrencyAdapter
    private lateinit var currencies: ArrayList<Currency>

    override val layoutId: Int
        get() = R.layout.home_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAllCurrency()
        adapter = CurrencyAdapter(this)
        rv_currency_list.adapter = adapter
        bt_convert.setOnClickListener(this)
    }

    private fun initDropdownList(currencies: ArrayList<Currency>) {
        val list = ArrayList<String>()
        currencies.forEach { list.add(it.name.toString()) }
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

    private fun observeCurrencyPrices(currencyName: String) {

        viewModel.refreshHomeList(currencyName).observe(viewLifecycleOwner, {
            when (it.getStatus()) {

                DataState.DataStatus.LOADING -> {
                    showLayoutLoading(false)
                }

                DataState.DataStatus.SUCCESS -> {
                    hideLayoutErrorAndLoading()
                    it?.getData()?.let { res ->
                        if (res.size > 0) {
                            currencies = res
                            adapter.addList(res)
                            adapter.notifyDataSetChanged()
                        }
                        else
                            showLayoutError(false, resources.getString(R.string.no_items))
                    } ?: run {
                        showLayoutError(false, resources.getString(R.string.error))
                    }
                }

                DataState.DataStatus.ERROR -> {
                    it?.getError()?.localizedMessage?.toString()?.let { err ->
                        showLayoutError(
                            false,
                            err
                        )
                    }
                }

                DataState.DataStatus.NO_INTERNET -> {
                    it?.getNoInternet()?.let {
                        showLayoutError(false, resources.getString(R.string.no_internet))
                    }
                }
            }
        })
    }

    override fun onItemClicked(view: View,currency: Currency) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.BUNDLE.SELECTED,currency)
        bundle.putString(Constants.BUNDLE.CURRENT,sp_currencies.selectedItem.toString())
        bundle.putParcelableArrayList(Constants.BUNDLE.LIST,currencies)
        navigationController.navigate(R.id.action_HomeFragment_to_ConverterFragment,bundle)
    }

    override fun onClick(p0: View?) {
        observeCurrencyPrices(sp_currencies.selectedItem.toString())
    }
}
package com.example.currencyapp.ui.converter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import com.example.currencyapp.R
import com.example.currencyapp.app.base.BaseFragment
import com.example.currencyapp.model.response.Currency
import com.example.currencyapp.utils.Constants
import kotlinx.android.synthetic.main.converter_fragment.*
import kotlinx.android.synthetic.main.header_currency_layout.*

class ConverterFragment : BaseFragment(), View.OnClickListener {

    private lateinit var currencies: ArrayList<Currency>
    private lateinit var outputCurrency: Currency
    private lateinit var current: String
    private var result: Double = 0.0
    private var amount: Double = 0.0

    override val layoutId: Int
        get() = R.layout.converter_fragment

    private fun initDropdownList(currencies: ArrayList<Currency>) {
        val list = ArrayList<String>()
        currencies.forEach { list.add(it.name.toString()) }
        val adapter =
            ArrayAdapter(activity?.applicationContext!!, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_currencies.adapter = adapter
        sp_currencies.setSelection(currencies.indexOf(currencies.find { it.name == current }))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initViewsState()
        et_amount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tv_amount.text = "???"
            }

            override fun afterTextChanged(editable: Editable?) {
            }
        })
    }

    private fun initData(){
        current = arguments?.getString(Constants.BUNDLE.CURRENT).toString()
        outputCurrency = arguments?.getParcelable<Currency>(Constants.BUNDLE.SELECTED) as Currency
        currencies =
            arguments?.getParcelableArrayList<Currency>(Constants.BUNDLE.LIST) as ArrayList<Currency>
    }

    private fun initViewsState() {
        bt_convert.setOnClickListener(this)
        initDropdownList(currencies)
        tv_currency.text = outputCurrency.symbol
        et_amount.setText("1")
        amount = et_amount.text.toString().toDouble()
        result = amount * outputCurrency.price.toString().toDouble()
        tv_amount.text = result.toString()
    }

    override fun onClick(p0: View?) {
        calculatePrice()
    }

    private fun calculatePrice(){
        amount = if (et_amount.text.toString().isNotEmpty()) {
            et_amount.text.toString().toDouble()
        } else {
            et_amount.setText("1")
            1.0
        }

        val selectedItemPrice : Double = currencies.find { it.name == sp_currencies.selectedItem.toString() }?.price!!
        result = amount * ( outputCurrency.price / selectedItemPrice)
        tv_amount.text = result.toString()
    }
}
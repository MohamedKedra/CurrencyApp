package com.example.currencyapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.currencyapp.R
import kotlinx.android.synthetic.main.header_currency_layout.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() ,OnItemClickedListener{

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDropdownList()
        rv_currency_list.adapter = CurrencyAdapter(this)
    }

    fun initDropdownList(){
        val list = ArrayList<String>()
        list.add("EGP")
        list.add("EUR")
        list.add("DOL")
        list.add("EGS")
        val adapter =
            ArrayAdapter(activity?.applicationContext!!, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_currencies.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onItemClicked(view: View) {

    }
}
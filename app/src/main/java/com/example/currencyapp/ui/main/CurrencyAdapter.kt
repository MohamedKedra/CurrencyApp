package com.example.currencyapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp.R

class CurrencyAdapter(private val onItemClickedListener: OnItemClickedListener) : RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {

    inner class CurrencyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemClickedListener.onItemClicked(itemView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency_layout,parent,false)
        return CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {

    }

    override fun getItemCount(): Int = 20
}
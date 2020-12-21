package com.example.currencyapp.ui.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyapp.R
import com.example.currencyapp.model.response.Currency
import kotlinx.android.synthetic.main.item_currency_layout.view.*

class CurrencyAdapter(private val onItemClickedListener: OnItemClickedListener) : RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {

    var list: List<Currency> = ArrayList()

    inner class CurrencyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemClickedListener.onItemClicked(itemView)
        }

    }

    fun addList(list: List<Currency>){
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency_layout,parent,false)
        return CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        val item = list[position]
        holder.itemView.tv_currency.text = item.name.plus("(").plus(item.symbol).plus(")")
        holder.itemView.tv_price.text = item.price.toString()
    }

    override fun getItemCount(): Int = list.size
}
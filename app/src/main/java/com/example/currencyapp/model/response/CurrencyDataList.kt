package com.example.currencyapp.model.response

import com.example.currencyapp.R

class CurrencyDataList {

    companion object{
        fun getInitList():ArrayList<Currency>{
            val currencyList = ArrayList<Currency>()
            currencyList.add(Currency(R.drawable.aed,"AED","",0.0))
            currencyList.add(Currency(R.drawable.chf,"CHF","",0.0))
            currencyList.add(Currency(R.drawable.cny,"CNY","",0.0))
            currencyList.add(Currency(R.drawable.egp,"EGP","",0.0))
            currencyList.add(Currency(R.drawable.eur,"EUR","",0.0))
            currencyList.add(Currency(R.drawable.gbp,"GBP","",0.0))
            currencyList.add(Currency(R.drawable.jpy,"JPY","",0.0))
            currencyList.add(Currency(R.drawable.kwd,"KWD","",0.0))
            currencyList.add(Currency(R.drawable.syp,"SYP","",0.0))
            currencyList.add(Currency(R.drawable.usd,"USD","",0.0))
            return currencyList
        }
    }

}
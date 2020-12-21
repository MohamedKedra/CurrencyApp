package com.example.currencyapp.model.response

class CurrencyDataList {

    companion object{

        fun getInitList():ArrayList<Currency>{
            val currencyList = ArrayList<Currency>()
            currencyList.add(Currency("EGP","",0.0))
            currencyList.add(Currency("EUR","",0.0))
            currencyList.add(Currency("GBP","",0.0))
            currencyList.add(Currency("USD","",0.0))
            return currencyList
        }

    }

}
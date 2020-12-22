package com.example.currencyapp.model.response

import android.os.Parcel
import android.os.Parcelable

data class Currency(val imageRes: Int, val symbol: String?, var name: String?, var price: Double) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageRes)
        parcel.writeString(symbol)
        parcel.writeString(name)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Currency> {
        override fun createFromParcel(parcel: Parcel): Currency {
            return Currency(parcel)
        }

        override fun newArray(size: Int): Array<Currency?> {
            return arrayOfNulls(size)
        }
    }
}
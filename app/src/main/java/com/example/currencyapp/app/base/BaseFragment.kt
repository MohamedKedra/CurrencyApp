package com.example.currencyapp.app.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.loading_layout.*

abstract class BaseFragment : Fragment() {

    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(layoutId, container, false)
    }

    fun showLayoutLoading(isInitData: Boolean) {
        if (isInitData)
            header_container.visibility = View.GONE
        else
            header_container.visibility = View.VISIBLE
        body_container.visibility = View.GONE
        pb_progressbar.visibility = View.VISIBLE
        tv_error.visibility = View.GONE
    }

    fun showLayoutError(isInitData: Boolean, error: String) {
        if (isInitData)
            header_container.visibility = View.GONE
        else
            header_container.visibility = View.VISIBLE
        body_container.visibility = View.GONE
        pb_progressbar.visibility = View.GONE
        tv_error.visibility = View.VISIBLE
        tv_error.text = error
    }

    fun hideLayoutErrorAndLoading() {
        header_container.visibility = View.VISIBLE
        body_container.visibility = View.VISIBLE
        pb_progressbar.visibility = View.GONE
        tv_error.visibility = View.GONE
    }
}
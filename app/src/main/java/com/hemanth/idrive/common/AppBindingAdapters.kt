package com.hemanth.idrive.common

import android.app.Activity
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

object AppBindingAdapters {

    @BindingAdapter("onRefresh")
    @JvmStatic
    fun setRefresh(view: SwipeRefreshLayout, listener: SwipeRefreshLayout.OnRefreshListener) {
        val newValue = SwipeRefreshLayout.OnRefreshListener {
            view.isRefreshing = false
            listener.onRefresh()
        }
        view.setOnRefreshListener(newValue)
    }

    @BindingAdapter("onBackClick")
    @JvmStatic
    fun setOnBackPress(view: View, value: Boolean) {
        view.setOnClickListener {
            val activity: Activity = view.context as Activity
            activity.onBackPressed()
        }
    }
}

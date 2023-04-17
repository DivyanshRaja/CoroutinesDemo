package com.example.coroutine_demo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coroutine_demo.data.api.ApiHelper
import com.example.coroutine_demo.data.local.DatabaseHelper
import com.example.coroutine_demo.ui.paraller_network_call.ParallelNetworkCallsViewModel
import com.example.coroutine_demo.ui.series_network_calls.SeriesNetworkCallsViewModel
import com.example.coroutine_demo.ui.single_network_call.SingleNetworkCallViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(SeriesNetworkCallsViewModel::class.java)) {
            return SeriesNetworkCallsViewModel(apiHelper, dbHelper) as T
        }

        if (modelClass.isAssignableFrom(ParallelNetworkCallsViewModel::class.java)) {
            return ParallelNetworkCallsViewModel(apiHelper, dbHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}
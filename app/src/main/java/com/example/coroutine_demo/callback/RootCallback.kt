package com.example.coroutine_demo.callback

import android.view.View

interface RootCallback<T> {
    fun onRootCallback(index: Int, data: T, type: CallbackType, view: View) {}
    fun onRootCallback(index: Int, data: T, type: CallbackType) {}
    fun onRootCallback(type: CallbackType) {}
    fun onRootCallback(index: Int, data: T, OfferType: T, type: CallbackType) {}
    fun onRootCallback(index: Int, toData: T,fromData: T, type: CallbackType, view: View) {}

}
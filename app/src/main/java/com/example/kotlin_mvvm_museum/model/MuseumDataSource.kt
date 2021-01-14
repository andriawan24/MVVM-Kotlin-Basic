package com.example.kotlin_mvvm_museum.model

import com.example.kotlin_mvvm_museum.data.OperationCallback

interface MuseumDataSource {
    fun retrieveMuseums(callback: OperationCallback<Museum>)
    fun cancel()
}
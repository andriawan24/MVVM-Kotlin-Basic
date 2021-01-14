package com.example.kotlin_mvvm_museum.model

import com.example.kotlin_mvvm_museum.data.OperationCallback

class MuseumRepository(private val museumDataSource: MuseumDataSource) {

    fun fetchMuseum(callback: OperationCallback<Museum>) {
        museumDataSource.retrieveMuseums(callback)
    }

    fun cancel() {
        museumDataSource.cancel()
    }

}
package com.example.kotlin_mvvm_museum.di

import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_mvvm_museum.data.ApiClient
import com.example.kotlin_mvvm_museum.data.MuseumRemoteDataSource
import com.example.kotlin_mvvm_museum.model.MuseumDataSource
import com.example.kotlin_mvvm_museum.model.MuseumRepository
import com.example.kotlin_mvvm_museum.viewmodel.ViewModelFactory

object Injection {

    private val museumDataSource: MuseumDataSource = MuseumRemoteDataSource(ApiClient)
    private val museumRepository = MuseumRepository(museumDataSource)
    private val museumViewModelFactory = ViewModelFactory(museumRepository)

    fun providerRepository(): MuseumDataSource {
        return museumDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return museumViewModelFactory
    }

}
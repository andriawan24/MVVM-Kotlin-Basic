package com.example.kotlin_mvvm_museum.data

import com.example.kotlin_mvvm_museum.model.Museum

data class MuseumResponse(val status: Int?, val msg: String?, val data: List<Museum>?) {
    fun isSuccess(): Boolean = (status == 200)
}
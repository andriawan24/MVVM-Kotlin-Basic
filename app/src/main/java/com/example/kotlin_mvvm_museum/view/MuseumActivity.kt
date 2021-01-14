package com.example.kotlin_mvvm_museum.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_mvvm_museum.R
import com.example.kotlin_mvvm_museum.di.Injection
import com.example.kotlin_mvvm_museum.model.Museum
import com.example.kotlin_mvvm_museum.viewmodel.MuseumViewModel
import kotlinx.android.synthetic.main.activity_museum.*
import kotlinx.android.synthetic.main.layout_error.*

class MuseumActivity : AppCompatActivity() {

    private lateinit var viewModel: MuseumViewModel
    private lateinit var adapter: MuseumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museum)

        setupViewModel()
        setUpUI()
    }

    private fun setUpUI() {
        adapter = MuseumAdapter(viewModel.museums.value ?: emptyList())
        rvMuseum.layoutManager = LinearLayoutManager(this)
        rvMuseum.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
                this,
                Injection.provideViewModelFactory()
        ).get(MuseumViewModel::class.java)

        viewModel.museums.observe(this, renderMuseums)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageObserver)
        viewModel.isEmptyList.observe(this, emptyListObserver)
    }

    // Observer

    private val renderMuseums = Observer<List<Museum>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility = View.GONE
        layoutEmpty.visibility = View.GONE
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressDialog.visibility = visibility
    }

    @SuppressLint("SetTextI18n")
    private val onMessageObserver = Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility = View.VISIBLE
        layoutEmpty.visibility = View.GONE
        tvError.text = "Error $it"
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility = View.VISIBLE
        layoutError.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadMuseums()
    }

    companion object {
        const val TAG = "CONSOLE"
    }
}
package com.example.coroutine_demo.ui.series_network_calls

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coroutine_demo.data.api.ApiHelperImpl
import com.example.coroutine_demo.data.api.RetrofitBuilder
import com.example.coroutine_demo.data.local.DatabaseBuilder
import com.example.coroutine_demo.data.local.DatabaseHelperImpl
import com.example.coroutine_demo.data.model.ApiUser
import com.example.coroutine_demo.databinding.ActivitySeriesNetworkCallsBinding
import com.example.coroutine_demo.ui.UiState
import com.example.coroutine_demo.ui.ViewModelFactory
import com.example.coroutine_demo.ui.adapter.AdapterUsers

class SeriesNetworkCallsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeriesNetworkCallsBinding

    private lateinit var viewModel: SeriesNetworkCallsViewModel
    private lateinit var adapter: AdapterUsers


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySeriesNetworkCallsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = AdapterUsers(arrayListOf())
        binding.recyclerView.adapter = adapter

        setupViewModel()
        setupObserver()
    }


    private fun setupObserver() {
        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    renderList(it.data)
                    binding.recyclerView.visibility = View.VISIBLE
                }
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                is UiState.Error -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun renderList(users: List<ApiUser>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        )[SeriesNetworkCallsViewModel::class.java]
    }

}
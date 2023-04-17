package com.example.coroutine_demo.ui.paraller_network_call

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.coroutine_demo.R
import com.example.coroutine_demo.data.api.ApiHelperImpl
import com.example.coroutine_demo.data.api.RetrofitBuilder
import com.example.coroutine_demo.data.local.DatabaseBuilder
import com.example.coroutine_demo.data.local.DatabaseHelperImpl
import com.example.coroutine_demo.data.model.ApiUser
import com.example.coroutine_demo.databinding.ActivityParallelNetworkCallsBinding
import com.example.coroutine_demo.databinding.ActivitySingleNetworkCallBinding
import com.example.coroutine_demo.ui.UiState
import com.example.coroutine_demo.ui.ViewModelFactory
import com.example.coroutine_demo.ui.adapter.AdapterUsers
import com.example.coroutine_demo.ui.single_network_call.SingleNetworkCallViewModel

class ParallelNetworkCallsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParallelNetworkCallsBinding

    private lateinit var viewModel: ParallelNetworkCallsViewModel
    private lateinit var adapter: AdapterUsers


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParallelNetworkCallsBinding.inflate(layoutInflater)
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
        )[ParallelNetworkCallsViewModel::class.java]
    }
}
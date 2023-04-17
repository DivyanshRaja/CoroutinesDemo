package com.example.coroutine_demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutine_demo.databinding.ActivityMainBinding
import com.example.coroutine_demo.ui.paraller_network_call.ParallelNetworkCallsActivity
import com.example.coroutine_demo.ui.series_network_calls.SeriesNetworkCallsActivity
import com.example.coroutine_demo.ui.single_network_call.SingleNetworkCallActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        binding.btnSingleNetWorkCall.setOnClickListener {
            startActivity(Intent(this@MainActivity, SingleNetworkCallActivity::class.java))

        }
        binding.btnSeriesNetWorkCall.setOnClickListener {
            startActivity(Intent(this@MainActivity, SeriesNetworkCallsActivity::class.java))
        }

        binding.btnParallelNetWorkCall.setOnClickListener {
            startActivity(Intent(this@MainActivity, ParallelNetworkCallsActivity::class.java))

        }
    }

}
package com.example.coroutine_demo.ui.awaitjoin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutine_demo.databinding.ActivityJoinAndAwaitDemoBinding

class JoinAndAwaitDemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinAndAwaitDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJoinAndAwaitDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
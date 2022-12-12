package com.example.mediumrair.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mediumrair.databinding.ActivityHomeBinding
import com.example.mediumrair.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
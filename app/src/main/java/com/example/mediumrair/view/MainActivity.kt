package com.example.mediumrair.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mediumrair.databinding.ActivityMainBinding
import com.example.mediumrair.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewBind: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        mainViewModel = ViewModelProvider(this) [MainViewModel::class.java]

        listener()
    }

    private fun listener() {
        viewBind.mainBtnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        viewBind.mainBtnRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
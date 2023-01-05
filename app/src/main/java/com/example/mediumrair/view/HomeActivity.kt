package com.example.mediumrair.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mediumrair.R
import com.example.mediumrair.databinding.ActivityHomeBinding
import com.example.mediumrair.view.fragment.HomeFragment
import com.example.mediumrair.view.fragment.LevelFragment
import com.example.mediumrair.view.fragment.ProfileFragment
import com.example.mediumrair.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityHomeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        setContentView(R.layout.activity_home)
        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.userBottomNavBar) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_menu -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.level_menu -> {
                    loadFragment(LevelFragment())
                    true
                }
                R.id.profile_menu -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> throw AssertionError()
            }
        }
    }
    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.userFragmentFrameLayout,fragment)
        transaction.commit()
    }
}
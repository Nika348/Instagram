package com.example.instagram

import android.os.Bundle
import com.example.instagram.databinding.ActivityHomeBinding
import com.example.instagram.databinding.BottomNavigationViewBinding

class SearchActivity : BaseActivity(1) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        val bnvBinding = BottomNavigationViewBinding.bind(binding.root)
        setContentView(binding.root)
        setupBottomNavigation(bnvBinding)
    }
}
package com.example.instagram

import android.content.Intent
import android.os.Bundle
import com.example.instagram.databinding.ActivityHomeBinding
import com.example.instagram.databinding.ActivityProfileBinding
import com.example.instagram.databinding.BottomNavigationViewBinding


class ProfileActivity : BaseActivity(4) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfileBinding.inflate(layoutInflater)
        val bnvBinding = BottomNavigationViewBinding.bind(binding.root)
        setContentView(binding.root)
        setupBottomNavigation(bnvBinding)

        binding.editProfileBtn.setOnClickListener{
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

    }
}
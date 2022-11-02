package com.example.instagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instagram.databinding.ActivityEditProfileBinding
import com.example.instagram.databinding.ActivityProfileBinding

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        val binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.closeImage.setOnClickListener{
            finish()
        }
    }
}
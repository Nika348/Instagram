package com.example.instagram.activities

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.R
import com.example.instagram.databinding.ActivityEditProfileBinding
import com.example.instagram.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        val binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.closeImage.setOnClickListener{
            finish()
        }

        val auth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance().reference
        database.child("users").child(auth.currentUser!!.uid)
            .addListenerForSingleValueEvent(ValueEventListenerAdapter{
                val user = it.getValue(User::class.java)
                binding.nameInput.setText(user!!.name, TextView.BufferType.EDITABLE)
                binding.usernameInput.setText(user.username, TextView.BufferType.EDITABLE)
                binding.websiteInput.setText(user.website, TextView.BufferType.EDITABLE)
                binding.bioInput.setText(user.bio, TextView.BufferType.EDITABLE)
                binding.emailInput.setText(user.email, TextView.BufferType.EDITABLE)
                binding.phoneInput.setText(user.phone.toString(), TextView.BufferType.EDITABLE)
            })
    }
}
package com.example.instagram.activities

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.instagram.R
import com.example.instagram.databinding.FragmentEditProfileBinding
import com.example.instagram.databinding.FragmentProfileBinding
import com.example.instagram.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentProfileBinding is null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEditProfileBinding.bind(view)
        setupListeners(view)

        val auth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance().reference
        database.child("users").child(auth.currentUser!!.uid).addListenerForSingleValueEvent(ValueEventListenerAdapter {
                val userData = it.getValue(User::class.java)
                binding.nameInput.setText(userData!!.name, TextView.BufferType.EDITABLE)
                binding.usernameInput.setText(userData.username, TextView.BufferType.EDITABLE)
                binding.websiteInput.setText(userData.website, TextView.BufferType.EDITABLE)
                binding.bioInput.setText(userData.bio, TextView.BufferType.EDITABLE)
                binding.emailInput.setText(userData.email, TextView.BufferType.EDITABLE)
                binding.phoneInput.setText(userData.phone.toString(), TextView.BufferType.EDITABLE)

        })
    }

    private fun setupListeners(view: View){
        binding.toolbar.setNavigationOnClickListener{
            Navigation.findNavController(view).navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//        val auth = FirebaseAuth.getInstance()
//        val database = FirebaseDatabase.getInstance().reference
//        database.child("users").child(auth.currentUser!!.uid)
//            .addListenerForSingleValueEvent(ValueEventListenerAdapter{
//                val user = it.getValue(User::class.java)
//                binding.nameInput.setText(user!!.name, TextView.BufferType.EDITABLE)
//                binding.usernameInput.setText(user.username, TextView.BufferType.EDITABLE)
//                binding.websiteInput.setText(user.website, TextView.BufferType.EDITABLE)
//                binding.bioInput.setText(user.bio, TextView.BufferType.EDITABLE)
//                binding.emailInput.setText(user.email, TextView.BufferType.EDITABLE)
//                binding.phoneInput.setText(user.phone.toString(), TextView.BufferType.EDITABLE)
//            })

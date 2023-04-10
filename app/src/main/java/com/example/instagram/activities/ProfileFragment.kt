package com.example.instagram.activities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.example.instagram.R
import com.example.instagram.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentProfileBinding is null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)
        setupListeners(view)
    }

    private fun setupListeners(view: View){
        binding.editProfileBtn.setOnClickListener {
            findNavController(view).navigate(R.id.action_profileFragment_to_editProfileFragment)
        }
    }


        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
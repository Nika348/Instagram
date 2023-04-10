package com.example.instagram.activities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.instagram.R
import com.example.instagram.databinding.FragmentEditProfileBinding
import com.example.instagram.databinding.FragmentProfileBinding

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentProfileBinding is null")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEditProfileBinding.bind(view)
        setupListeners(view)
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

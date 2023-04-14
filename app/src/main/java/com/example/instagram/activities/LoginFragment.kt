package com.example.instagram.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.instagram.R
import com.example.instagram.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentLoginBinding is null")

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        val editEmail = binding.emailInput
        val editPassword = binding.passwordInput
        val loginBtn = binding.loginBtn

        loginBtn.isEnabled = false
        editEmail.doAfterTextChanged {
            updateButtonState()
        }
        editPassword.doAfterTextChanged {
            updateButtonState()
        }
        firebaseAuth = FirebaseAuth.getInstance()
        setupListeners()

    }

    private fun updateButtonState() {
        val editEmail = binding.emailInput
        val editPassword = binding.passwordInput
        val loginBtn = binding.loginBtn

        loginBtn.isEnabled =
            editEmail.text.toString().isNotEmpty() && editPassword.text.toString().isNotEmpty()
    }

    private fun setupListeners() {
        binding.loginBtn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        findNavController().navigate(R.id.homeFragment)
                    }
                }.addOnFailureListener {
                    Log.e("Tag", "onError: ${it.message}")
                }
            } else {
                Toast.makeText(
                    requireActivity(),
                    "Please enter email and password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.createAccountText.setOnClickListener {
            findNavController().navigate(R.id.emailFragment)
        }

    }
}
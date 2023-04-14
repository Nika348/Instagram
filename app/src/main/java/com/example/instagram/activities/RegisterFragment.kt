package com.example.instagram.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.instagram.R
import com.example.instagram.databinding.FragmentRegisterEmailBinding
import com.example.instagram.databinding.FragmentRegisterNamepassBinding
import com.example.instagram.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


//1 email, next button
class EmailFragment : Fragment(R.layout.fragment_register_email) {

    private var _binding: FragmentRegisterEmailBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentRegisterEmailBinding is null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterEmailBinding.bind(view)

        binding.nextBtn.setOnClickListener {
            val email = binding.emailInput.text.toString()
            if (email.isNotEmpty()) {
                val action = EmailFragmentDirections.actionEmailFragmentToNamePassFragment(email)
                findNavController().navigate(action)
            } else {
                Toast.makeText(
                    requireActivity(),
                    "Please enter email",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

//2 full name, password, register btn
class NamePassFragment : Fragment(R.layout.fragment_register_namepass) {

    private var _binding: FragmentRegisterNamepassBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentRegisterNamepassBinding is null")

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var _DataBase: DatabaseReference

    private val args by navArgs<NamePassFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterNamepassBinding.bind(view)

        binding.registerBtn.setOnClickListener {
            val fullName = binding.fullNameInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val email = args.email
            onRegister(fullName, password, email)
        }
    }

    private fun onRegister(fullName: String, password: String, email: String) {
        firebaseAuth = FirebaseAuth.getInstance()
        _DataBase = FirebaseDatabase.getInstance().getReference("users")
        if (fullName.isNotEmpty() && password.isNotEmpty()) {
            if (email != null) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val user = makeUser(fullName, email)
                            _DataBase.child("users").child(it.result.user!!.uid).setValue(user)
                            if (it.isSuccessful) {
                                findNavController().navigate(R.id.homeFragment)
                            } else {
                                Log.e("Tag", "failed to create user profile", it.exception)
                                Toast.makeText(
                                    requireActivity(),
                                    "Something went wrong. Try again later",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            Log.e("Tag", "onRegister: Email is null")
                            Toast.makeText(
                                requireActivity(),
                                "Please enter email",
                                Toast.LENGTH_SHORT
                            ).show()
                            childFragmentManager.popBackStack()

                        }
                    }
            } else {
                Toast.makeText(
                    requireActivity(),
                    "Please enter Full Name and Password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun username(fullName: String): String {
        return fullName.toLowerCase().replace(" ", ".")
    }

    private fun makeUser(fullName: String, email: String): User {
        val username = username(fullName)
        return User(name = fullName, username = username, email = email)
    }
}




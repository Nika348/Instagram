package com.example.instagram.activities

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.instagram.R
import com.example.instagram.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentHomeBinding is null")

    private lateinit var _auth: FirebaseAuth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val _auth = FirebaseAuth.getInstance()
        //_auth.signOut()
        if (_auth.currentUser == null) {
            _auth.addAuthStateListener {
                findNavController().navigate(R.id.loginFragment)
            }
        }

        binding.signOutText.setOnClickListener{
            _auth.signOut()
        }
        _auth.addAuthStateListener {
            if(it.currentUser == null){
                findNavController().navigate(R.id.loginFragment)
            }
        }
//        _auth.signInWithEmailAndPassword("123@mail.ru", "password")
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    Log.d(TAG, "Successfully signed in with email link!")
//                }else {
//                    Log.e(TAG, "Error signing in with email link", it.exception)
//                }
//                }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
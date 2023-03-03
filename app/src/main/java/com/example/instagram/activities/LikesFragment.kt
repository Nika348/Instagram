package com.example.instagram.activities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.FragmentLikesBinding

class LikesFragment : Fragment(R.layout.fragment_likes) {

    private var _binding: FragmentLikesBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentLikesBinding is null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLikesBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
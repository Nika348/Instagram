package com.example.instagram.activities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.FragmentShareBinding

class ShareFragment : Fragment(R.layout.fragment_share) {

    private var _binding: FragmentShareBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentShareBinding is null")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShareBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
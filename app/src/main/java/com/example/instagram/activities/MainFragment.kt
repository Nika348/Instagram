package com.example.instagram.activities

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.instagram.R
import com.example.instagram.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding ?: throw NullPointerException("FragmentMainBinding is null")



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        setUp()

    }

    fun setUp(){
        val bottomNavigation = binding.bottomNavigationView
        val navHost = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,
                R.id.likesFragment,
                R.id.profileFragment,
                R.id.searchFragment,
                R.id.shareFragment -> bottomNavigation.visibility =
                    View.VISIBLE
                else -> bottomNavigation.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
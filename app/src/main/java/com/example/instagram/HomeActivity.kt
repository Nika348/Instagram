package com.example.instagram

import android.content.Intent
import android.os.Bundle
import com.example.instagram.databinding.ActivityHomeBinding
import com.example.instagram.databinding.BottomNavigationViewBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : BaseActivity(0) {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        val bnvBinding = BottomNavigationViewBinding.bind(binding.root)
        setContentView(binding.root)
        setupBottomNavigation(bnvBinding)

        mAuth = FirebaseAuth.getInstance()
        mAuth.signOut()
        //auth.signInWithEmailAndPassword("veronika99k@yandex.ru", "password")
    }

    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser == null){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
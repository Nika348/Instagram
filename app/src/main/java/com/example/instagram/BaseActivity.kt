package com.example.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.databinding.ActivityHomeBinding
import com.example.instagram.databinding.ActivityProfileBinding
import com.example.instagram.databinding.BottomNavigationViewBinding

abstract class BaseActivity(val navNumber : Int) : AppCompatActivity() {
        fun setupBottomNavigation(bnvBiding: BottomNavigationViewBinding) {

            with(bnvBiding) {
                bottomNavigationView.setIconSize(29f, 29f)
                bottomNavigationView.setTextVisibility(false)
                bottomNavigationView.enableItemShiftingMode(false)
                bottomNavigationView.enableShiftingMode(false)
                bottomNavigationView.enableAnimation(false)
                for (i in 0 until bottomNavigationView.menu.size()) {
                    bottomNavigationView.setIconTintList(i, null)
                }
            }

            bnvBiding.bottomNavigationView.setOnNavigationItemSelectedListener {
                val nextActivity =
                    when(it.itemId){
                        R.id.nav_item_home -> HomeActivity::class.java
                        R.id.nav_item_search -> SearchActivity::class.java
                        R.id.nav_item_share -> ShareActivity::class.java
                        R.id.nav_item_like -> LikesActivity::class.java
                        R.id.nav_item_profile -> ProfileActivity::class.java
                        else -> null
                    }
                if(nextActivity != null){
                    val intent = Intent(this, nextActivity)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                    true
                } else {
                    false
                }
            }
            bnvBiding.bottomNavigationView.menu.getItem(navNumber).isChecked = true
        }
}
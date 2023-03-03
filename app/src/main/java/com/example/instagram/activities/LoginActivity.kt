package com.example.instagram.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.instagram.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

class LoginActivity : AppCompatActivity(), KeyboardVisibilityEventListener, TextWatcher,
    View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        KeyboardVisibilityEvent.setEventListener(this, this)
        binding.loginBtn.isEnabled = false
        binding.emailInput.addTextChangedListener(this)
        binding.passwordInput.addTextChangedListener(this)
        binding.loginBtn.setOnClickListener(this)

        firebaseAuth = FirebaseAuth.getInstance()
    }

    override fun onVisibilityChanged(isKeyboardOpen: Boolean) {
        if (isKeyboardOpen) {
            binding.scrollView.scrollTo(0, binding.scrollView.bottom)
            binding.createAccountText.visibility = View.GONE
        } else {
            binding.scrollView.scrollTo(0, binding.scrollView.top)
            binding.createAccountText.visibility = View.VISIBLE
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        binding.loginBtn.isEnabled =
            validate(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString()
            )
    }

    private fun validate(email: String, password: String) =
        email.isNotEmpty() && password.isNotEmpty()

    override fun onClick(view: View) {
        val email = binding.emailInput.text.toString()
        val password = binding.passwordInput.text.toString()
        if (validate(email, password)) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    startActivity(Intent(this, HomeFragment::class.java))
                    finish()
                }
            }
        } else {
            showToast("Please enter email and password")
        }
    }
}
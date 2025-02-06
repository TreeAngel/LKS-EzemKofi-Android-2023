package com.example.ezemkofi.activity

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.ezemkofi.R
import com.example.ezemkofi.databinding.ActivityRegisterBinding
import com.example.ezemkofi.util.api.viewModel.AuthViewModel
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this@RegisterActivity)[AuthViewModel::class.java]
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }
        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val fullname = binding.etFullname.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()
            if (username.isEmpty() || fullname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Fill all data to create an account",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this@RegisterActivity, "Invalid email address", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (confirmPassword != password) {
                Toast.makeText(this@RegisterActivity, "Confirm your password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
        }
    }
}
package com.example.ezemkofi.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.ezemkofi.R
import com.example.ezemkofi.databinding.ActivityLoginBinding
import com.example.ezemkofi.model.request.LoginRequest
import com.example.ezemkofi.util.api.retrofit.RetrofitClient
import com.example.ezemkofi.util.api.viewModel.AuthViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this@LoginActivity)[AuthViewModel::class.java]
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    binding.root.context,
                    "Enter your username and password",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            viewModel.login(LoginRequest(password, username))
            if (!RetrofitClient.getToken().isNullOrEmpty()) Toast.makeText(
                this@LoginActivity,
                "Success login/n ${RetrofitClient.getToken()}",
                Toast.LENGTH_SHORT
            ).show()
            else Toast.makeText(
                this@LoginActivity,
                "Fail login, invalid credentials",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        binding.tvCreateAccount.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}
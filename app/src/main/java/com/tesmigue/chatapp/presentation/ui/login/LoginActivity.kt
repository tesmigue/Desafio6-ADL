package com.tesmigue.chatapp.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tesmigue.chatapp.databinding.ActivityLoginBinding
import com.tesmigue.chatapp.presentation.viewmodel.LoginViewModel
import com.tesmigue.chatapp.presentation.ui.salas.SalasActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeViewModel() {
        viewModel.loginSuccess.observe(this) { success ->
            if (success) {
                startActivity(Intent(this, SalasActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
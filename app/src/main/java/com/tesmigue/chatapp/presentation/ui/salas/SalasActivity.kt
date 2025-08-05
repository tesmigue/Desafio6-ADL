package com.tesmigue.chatapp.presentation.ui.salas

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tesmigue.chatapp.databinding.ActivitySalasBinding
import com.tesmigue.chatapp.presentation.adapter.SalaAdapter
import com.tesmigue.chatapp.presentation.viewmodel.SalasViewModel
import com.tesmigue.chatapp.presentation.ui.chat.ChatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SalasActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySalasBinding
    private val viewModel: SalasViewModel by viewModels()
    private lateinit var adapter: SalaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SalaAdapter { sala ->
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("salaId", sala.id)
            intent.putExtra("salaNombre", sala.nombre)
            startActivity(intent)
        }

        binding.rvSalas.layoutManager = LinearLayoutManager(this)
        binding.rvSalas.adapter = adapter

        viewModel.salas.observe(this) { salas ->
            adapter.submitList(salas)
        }
    }
}

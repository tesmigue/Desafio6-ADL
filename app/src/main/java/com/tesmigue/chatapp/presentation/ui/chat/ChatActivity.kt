package com.tesmigue.chatapp.presentation.ui.chat

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tesmigue.chatapp.databinding.ActivityChatBinding
import com.tesmigue.chatapp.presentation.adapter.MensajeAdapter
import com.tesmigue.chatapp.presentation.adapter.MensajeAdapter.MensajeUi
import com.tesmigue.chatapp.presentation.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private lateinit var adapter: MensajeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val salaId = intent.getStringExtra("salaId") ?: "1"
        val salaNombre = intent.getStringExtra("salaNombre") ?: "Chat"

        title = salaNombre

        setupRecyclerView()
        observeViewModel()
        setupClickListeners(salaId)

        viewModel.conectarWebSocket(salaId)
    }

    private fun setupRecyclerView() {
        adapter = MensajeAdapter()
        binding.rvMensajes.layoutManager = LinearLayoutManager(this)
        binding.rvMensajes.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.mensajes.observe(this) { mensajes ->
            val mensajesUi = mensajes.map { mensaje ->
                when {
                    mensaje.contains("[Imagen]:") -> {
                        val uri = mensaje.substringAfter("[Imagen]:").trim()
                        MensajeUi(texto = "Yo envió una imagen:", uriImagen = uri)
                    }
                    mensaje.contains("[Archivo]") -> {
                        val nombreArchivo = mensaje.substringAfter("[Archivo]").trim()
                        MensajeUi(texto = "Yo envió un archivo:", uriArchivo = "mock_uri", nombreArchivo = nombreArchivo)
                    }
                    else -> MensajeUi(texto = mensaje)
                }
            }
            adapter.submitList(mensajesUi)

            if (mensajesUi.isNotEmpty()) {
                binding.rvMensajes.scrollToPosition(mensajesUi.size - 1)
            }
        }
    }

    private fun setupClickListeners(salaId: String) {
        binding.btnEnviar.setOnClickListener {
            val texto = binding.etMensaje.text.toString().trim()
            if (texto.isNotBlank()) {
                viewModel.enviarMensaje(texto)
                binding.etMensaje.text?.clear()
            }
        }

        binding.btnImagen.setOnClickListener {
            launcherImagen.launch("image/*")
        }

        binding.btnAdjuntar.setOnClickListener {
            launcherArchivo.launch("*/*")
        }
    }

    private val launcherImagen = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val salaId = intent.getStringExtra("salaId") ?: "1"
            viewModel.enviarImagen(it.toString(), salaId)
        }
    }

    private val launcherArchivo = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val salaId = intent.getStringExtra("salaId") ?: "1"
            val nombre = uri.lastPathSegment?.substringAfterLast("/") ?: "Archivo"
            viewModel.enviarArchivo(it.toString(), nombre, salaId)
        }
    }
}

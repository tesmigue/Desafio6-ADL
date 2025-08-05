package com.tesmigue.chatapp.presentation.viewmodel

import androidx.lifecycle.*
import com.tesmigue.chatapp.domain.usecase.ConectarChatUseCase
import com.tesmigue.chatapp.domain.usecase.EnviarMensajeUseCase
import com.tesmigue.chatapp.domain.repository.ChatLocalRepository
import com.tesmigue.chatapp.domain.model.Mensaje
import com.tesmigue.chatapp.domain.model.EstadoMensaje
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val conectar: ConectarChatUseCase,
    private val enviar: EnviarMensajeUseCase,
    private val chatLocal: ChatLocalRepository
) : ViewModel() {

    private val _mensajes = MutableLiveData<MutableList<String>>(mutableListOf())
    val mensajes: LiveData<MutableList<String>> = _mensajes

    fun conectarWebSocket(salaId: String) {
        viewModelScope.launch {
            cargarMensajesLocalmente(salaId)
            conectar.conectar(salaId) { nuevoMensaje ->
                viewModelScope.launch {
                    val texto = "${nuevoMensaje.remitente}: ${nuevoMensaje.contenido}"
                    _mensajes.value?.add(texto)
                    _mensajes.postValue(_mensajes.value)
                    chatLocal.guardarMensaje(nuevoMensaje, salaId)
                }
            }
        }
    }

    fun enviarMensaje(texto: String) {
        enviar.enviar(texto)
        val mensaje = "Yo: $texto"
        _mensajes.value?.add(mensaje)
        _mensajes.postValue(_mensajes.value)
    }

    suspend fun cargarMensajesLocalmente(salaId: String) {
        try {
            val mensajes = chatLocal.obtenerMensajes(salaId)
            _mensajes.postValue(mensajes.map {
                "${it.remitente}: ${it.contenido} (${it.estado.name.lowercase()})"
            }.toMutableList())
        } catch (e: Exception) {
            _mensajes.postValue(mutableListOf())
        }
    }

    fun enviarImagen(uri: String, salaId: String) {
        viewModelScope.launch {
            val mensaje = Mensaje(
                contenido = "[Imagen]: $uri",
                remitente = "Yo",
                timestamp = System.currentTimeMillis(),
                estado = EstadoMensaje.ENVIADO
            )
            chatLocal.guardarMensaje(mensaje, salaId)
            _mensajes.value?.add("${mensaje.remitente}: ${mensaje.contenido}")
            _mensajes.postValue(_mensajes.value)
        }
    }

    fun enviarArchivo(uri: String, nombreArchivo: String, salaId: String) {
        viewModelScope.launch {
            val mensaje = Mensaje(
                contenido = "[Archivo] $nombreArchivo",
                remitente = "Yo",
                timestamp = System.currentTimeMillis(),
                estado = EstadoMensaje.ENVIADO
            )

            chatLocal.guardarMensaje(mensaje, salaId)

            _mensajes.value?.add("${mensaje.remitente}: ${mensaje.contenido}")
            _mensajes.postValue(_mensajes.value)
        }
    }
}
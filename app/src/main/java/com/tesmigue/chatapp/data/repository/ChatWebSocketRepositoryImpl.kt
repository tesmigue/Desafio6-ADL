package com.tesmigue.chatapp.data.repository

import com.tesmigue.chatapp.data.datasource.websocket.ChatWebSocketClient
import com.tesmigue.chatapp.domain.model.Mensaje
import com.tesmigue.chatapp.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class ChatWebSocketRepositoryImpl @Inject constructor(
    private val client: ChatWebSocketClient
) : ChatRepository {

    private val sharedFlow = MutableSharedFlow<Mensaje>()

    override fun conectar(salaId: String, onMensaje: (Mensaje) -> Unit) {
        client.connect(salaId, object : ChatWebSocketClient.ChatWebSocketListener() {
            override fun onNuevoMensaje(mensajeTexto: String) {
                val mensaje = Mensaje(
                    contenido = mensajeTexto,
                    remitente = "servidor",
                    timestamp = System.currentTimeMillis()
                )
                onMensaje(mensaje)
                sharedFlow.tryEmit(mensaje)
            }
        })
    }

    override fun recibirMensajes(): Flow<Mensaje> = sharedFlow

    override fun enviarMensaje(mensaje: String) {
        client.sendMessage(mensaje)
    }

    override fun cerrarConexion() {
        client.close()
    }
}

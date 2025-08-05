package com.tesmigue.chatapp.domain.repository

import com.tesmigue.chatapp.domain.model.Mensaje
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    fun conectar(salaId: String, onMensaje: (Mensaje) -> Unit)
    fun recibirMensajes(): Flow<Mensaje>
    fun enviarMensaje(mensaje: String)
    fun cerrarConexion()
}

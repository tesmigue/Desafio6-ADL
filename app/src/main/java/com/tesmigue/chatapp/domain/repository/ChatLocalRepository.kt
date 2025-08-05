package com.tesmigue.chatapp.domain.repository

import com.tesmigue.chatapp.domain.model.EstadoMensaje
import com.tesmigue.chatapp.domain.model.Mensaje

interface ChatLocalRepository {
    suspend fun obtenerMensajes(salaId: String): List<Mensaje>
    suspend fun guardarMensaje(mensaje: Mensaje, salaId: String)
    suspend fun actualizarEstado(timestamp: Long, estado: EstadoMensaje)
}

package com.tesmigue.chatapp.domain.repository

import com.tesmigue.chatapp.data.database.entity.MensajeEntity
import com.tesmigue.chatapp.data.database.dao.MensajeDao
import com.tesmigue.chatapp.domain.model.EstadoMensaje
import com.tesmigue.chatapp.domain.model.Mensaje
import javax.inject.Inject

class ChatLocalRepositoryImpl @Inject constructor(
    private val dao: MensajeDao
) : ChatLocalRepository {

    override suspend fun obtenerMensajes(salaId: String): List<Mensaje> {
        return dao.getMensajesPorSala(salaId).map {
            Mensaje(it.contenido, it.remitente, it.timestamp)
        }
    }

    override suspend fun guardarMensaje(mensaje: Mensaje, salaId: String) {
        dao.insertarMensaje(
            MensajeEntity(
                contenido = mensaje.contenido,
                remitente = mensaje.remitente,
                timestamp = mensaje.timestamp,
                salaId = salaId
            )
        )
    }

    override suspend fun actualizarEstado(timestamp: Long, estado: EstadoMensaje) {
        TODO("Not yet implemented")
    }
}
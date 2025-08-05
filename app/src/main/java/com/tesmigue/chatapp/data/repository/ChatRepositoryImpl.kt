package com.tesmigue.chatapp.data.repository

import com.tesmigue.chatapp.data.database.dao.MensajeDao
import com.tesmigue.chatapp.data.database.entity.MensajeEntity
import com.tesmigue.chatapp.domain.model.Mensaje
import com.tesmigue.chatapp.domain.model.EstadoMensaje
import com.tesmigue.chatapp.domain.repository.ChatLocalRepository
import javax.inject.Inject


class ChatLocalRepositoryImpl @Inject constructor(
    private val dao: MensajeDao
) : ChatLocalRepository {

    override suspend fun obtenerMensajes(salaId: String): List<Mensaje> {
        return dao.getMensajesPorSala(salaId).map {
            Mensaje(
                contenido = it.contenido,
                remitente = it.remitente,
                timestamp = it.timestamp,
                estado = if (it.estado.isNotEmpty()) {
                    try {
                        EstadoMensaje.valueOf(it.estado)
                    } catch (e: IllegalArgumentException) {
                        EstadoMensaje.ENVIADO
                    }
                } else {
                    EstadoMensaje.ENVIADO
                }
            )
        }
    }

    override suspend fun guardarMensaje(mensaje: Mensaje, salaId: String) {
        dao.insertarMensaje(
            MensajeEntity(
                contenido = mensaje.contenido,
                remitente = mensaje.remitente,
                timestamp = mensaje.timestamp,
                salaId = salaId,
                estado = mensaje.estado.name
            )
        )
    }

    override suspend fun actualizarEstado(timestamp: Long, estado: EstadoMensaje) {
        dao.actualizarEstadoMensaje(timestamp, estado.name)
    }
}
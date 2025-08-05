package com.tesmigue.chatapp.data.database.dao

import androidx.room.*
import com.tesmigue.chatapp.data.database.entity.MensajeEntity

@Dao
interface MensajeDao {

    @Query("SELECT * FROM mensajes WHERE salaId = :salaId ORDER BY timestamp ASC")
    suspend fun getMensajesPorSala(salaId: String): List<MensajeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarMensaje(mensaje: MensajeEntity)

    @Query("DELETE FROM mensajes WHERE salaId = :salaId")
    suspend fun borrarMensajesDeSala(salaId: String)

    @Query("UPDATE mensajes SET estado = :nuevoEstado WHERE timestamp = :timestamp")
    suspend fun actualizarEstadoMensaje(timestamp: Long, nuevoEstado: String)
}
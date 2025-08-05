package com.tesmigue.chatapp.domain.repository

import com.tesmigue.chatapp.domain.model.Sala

interface SalaRepository {
    suspend fun obtenerSalas(): List<Sala>
}

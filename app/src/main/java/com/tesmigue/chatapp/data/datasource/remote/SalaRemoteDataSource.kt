package com.tesmigue.chatapp.data.datasource.remote

import com.tesmigue.chatapp.domain.model.Sala

interface SalaRemoteDataSource {
    suspend fun obtenerSalas(): List<Sala>
}

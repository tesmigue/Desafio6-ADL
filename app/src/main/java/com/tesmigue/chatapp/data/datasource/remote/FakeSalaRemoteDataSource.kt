package com.tesmigue.chatapp.data.datasource.remote

import com.tesmigue.chatapp.domain.model.Sala
import javax.inject.Inject

class FakeSalaRemoteDataSource @Inject constructor(): SalaRemoteDataSource {
    override suspend fun obtenerSalas(): List<Sala> {
        return listOf(
            Sala("1", "General", "Conversaciones generales"),
            Sala("2", "Música", "Charlas sobre música"),
            Sala("3", "Noticias", "Últimas noticias"),
            Sala("4", "Gaming", "Videojuegos y más")
        )
    }
}

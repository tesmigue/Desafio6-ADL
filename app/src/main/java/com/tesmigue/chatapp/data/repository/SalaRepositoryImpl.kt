package com.tesmigue.chatapp.data.repository

import com.tesmigue.chatapp.data.datasource.remote.SalaRemoteDataSource
import com.tesmigue.chatapp.domain.model.Sala
import com.tesmigue.chatapp.domain.repository.SalaRepository
import javax.inject.Inject

class SalaRepositoryImpl @Inject constructor(
    private val remote: SalaRemoteDataSource
) : SalaRepository {
    override suspend fun obtenerSalas(): List<Sala> {
        return remote.obtenerSalas()
    }
}

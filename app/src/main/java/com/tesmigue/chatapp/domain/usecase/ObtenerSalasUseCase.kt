package com.tesmigue.chatapp.domain.usecase

import com.tesmigue.chatapp.domain.model.Sala
import com.tesmigue.chatapp.domain.repository.SalaRepository
import javax.inject.Inject

class ObtenerSalasUseCase @Inject constructor(
    private val repository: SalaRepository
) {
    suspend operator fun invoke(): List<Sala> = repository.obtenerSalas()
}

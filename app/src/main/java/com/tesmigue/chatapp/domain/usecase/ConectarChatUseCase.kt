package com.tesmigue.chatapp.domain.usecase

import com.tesmigue.chatapp.domain.model.Mensaje
import com.tesmigue.chatapp.domain.repository.ChatRepository
import javax.inject.Inject

class ConectarChatUseCase @Inject constructor(
    private val repo: ChatRepository
) {
    fun conectar(salaId: String, onMensaje: (Mensaje) -> Unit) {
        repo.conectar(salaId, onMensaje)
    }
}

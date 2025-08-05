package com.tesmigue.chatapp.domain.usecase

import com.tesmigue.chatapp.domain.repository.ChatRepository
import javax.inject.Inject

class EnviarMensajeUseCase @Inject constructor(
    private val repo: ChatRepository
) {
    fun enviar(mensaje: String) = repo.enviarMensaje(mensaje)
}

package com.tesmigue.chatapp.data.datasource.websocket

import okhttp3.*
import okio.ByteString
import javax.inject.Inject

class ChatWebSocketClient @Inject constructor() {

    private var webSocket: WebSocket? = null
    private var listener: ChatWebSocketListener? = null

    fun connect(salaId: String, listener: ChatWebSocketListener) {
        this.listener = listener

        val url = "ws://10.0.2.2:8080/chat/$salaId" // Usa tu IP local o dirección pública del servidor

        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        webSocket = client.newWebSocket(request, listener)
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun close() {
        webSocket?.close(1000, "Cliente salió")
    }

    abstract class ChatWebSocketListener : WebSocketListener() {
        abstract fun onNuevoMensaje(mensaje: String)

        override fun onMessage(webSocket: WebSocket, text: String) {
            onNuevoMensaje(text)
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            t.printStackTrace()
        }
    }
}

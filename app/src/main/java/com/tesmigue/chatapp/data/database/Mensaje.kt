package com.tesmigue.chatapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mensaje(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val contenido: String,
    val estado: String // "enviado", "entregado", "leído"
)

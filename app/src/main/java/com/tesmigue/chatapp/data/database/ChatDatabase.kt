package com.tesmigue.chatapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tesmigue.chatapp.data.database.dao.MensajeDao
import com.tesmigue.chatapp.data.database.entity.MensajeEntity

@Database(entities = [MensajeEntity::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun mensajeDao(): MensajeDao
}

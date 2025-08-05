package com.tesmigue.chatapp.di

import android.content.Context
import androidx.room.Room
import com.tesmigue.chatapp.data.database.ChatDatabase
import com.tesmigue.chatapp.data.database.dao.MensajeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): ChatDatabase {
        return Room.databaseBuilder(appContext, ChatDatabase::class.java, "chat-db").build()
    }
    @Provides
    fun provideMensajeDao(db: ChatDatabase): MensajeDao = db.mensajeDao()
}

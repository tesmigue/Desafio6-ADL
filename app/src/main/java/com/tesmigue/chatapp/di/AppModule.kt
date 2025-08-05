package com.tesmigue.chatapp.di

import com.tesmigue.chatapp.data.database.dao.MensajeDao
import com.tesmigue.chatapp.data.datasource.remote.*
import com.tesmigue.chatapp.data.datasource.websocket.ChatWebSocketClient
import com.tesmigue.chatapp.data.repository.AuthRepositoryImpl
import com.tesmigue.chatapp.data.repository.ChatLocalRepositoryImpl
import com.tesmigue.chatapp.data.repository.ChatWebSocketRepositoryImpl
import com.tesmigue.chatapp.data.repository.SalaRepositoryImpl
import com.tesmigue.chatapp.domain.repository.ChatLocalRepository
import com.tesmigue.chatapp.domain.repository.ChatRepository
import com.tesmigue.chatapp.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideAuthRemoteDataSource(): AuthRemoteDataSource {
        return FakeAuthRemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        remote: AuthRemoteDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(remote)
    }


    @Provides
    @Singleton
    fun provideSalaRemoteDataSource(): SalaRemoteDataSource {
        return FakeSalaRemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideSalaRepository(
        remote: SalaRemoteDataSource
    ): SalaRepository {
        return SalaRepositoryImpl(remote)
    }

    @Provides
    @Singleton
    fun provideChatWebSocketClient(): ChatWebSocketClient {
        return ChatWebSocketClient()
    }

    @Provides
    @Singleton
    fun provideChatRepository(client: ChatWebSocketClient): ChatRepository {
        return ChatWebSocketRepositoryImpl(client)
    }


    @Provides
    @Singleton
    fun provideChatLocalRepository(dao: MensajeDao): ChatLocalRepository {
        return ChatLocalRepositoryImpl(dao)
    }


}
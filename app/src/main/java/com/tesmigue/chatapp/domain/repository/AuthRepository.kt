package com.tesmigue.chatapp.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): Boolean
}


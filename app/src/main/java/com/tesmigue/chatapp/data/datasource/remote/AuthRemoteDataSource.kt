package com.tesmigue.chatapp.data.datasource.remote

interface AuthRemoteDataSource {
    suspend fun login(email: String, password: String): Boolean
}

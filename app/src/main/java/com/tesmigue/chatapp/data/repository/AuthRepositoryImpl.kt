package com.tesmigue.chatapp.data.repository


import com.tesmigue.chatapp.data.datasource.remote.AuthRemoteDataSource
import com.tesmigue.chatapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun login(email: String, password: String): Boolean {
        return remote.login(email, password)
    }
}

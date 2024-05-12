package ru.hse.glimpse.network.api.unauthorized

import com.skydoves.sandwich.ApiResponse
import ru.hse.glimpse.network.api.unauthorized.model.AuthRequest
import ru.hse.glimpse.network.common.model.AuthNetworkResponse

class AuthRepository(
    private val authApi: AuthApi,
) {

    suspend fun login(email: String, password: String): ApiResponse<AuthNetworkResponse> {
        return authApi.login(AuthRequest(email, password))
    }

    suspend fun signup(email: String, password: String): ApiResponse<AuthNetworkResponse> {
        return authApi.signup(AuthRequest(email, password))
    }
}

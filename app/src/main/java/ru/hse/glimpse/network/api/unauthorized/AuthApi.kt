package ru.hse.glimpse.network.api.unauthorized

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import ru.hse.glimpse.network.api.unauthorized.model.AuthRequest
import ru.hse.glimpse.network.common.model.AuthNetworkResponse

interface AuthApi {

    @POST("auth/signup")
    suspend fun signup(@Body body: AuthRequest): ApiResponse<AuthNetworkResponse>

    @POST("auth/login")
    suspend fun login(@Body body: AuthRequest): ApiResponse<AuthNetworkResponse>
}

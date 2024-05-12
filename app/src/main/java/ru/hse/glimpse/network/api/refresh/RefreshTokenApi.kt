package ru.hse.glimpse.network.api.refresh

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import ru.hse.glimpse.network.common.model.AuthNetworkResponse

interface RefreshTokenApi {

    @GET("auth/refresh")
    suspend fun refresh(
        @Query("Refresh-Token") refreshToken: String
    ): ApiResponse<AuthNetworkResponse>
}

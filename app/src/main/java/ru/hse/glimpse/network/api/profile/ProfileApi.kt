package ru.hse.glimpse.network.api.profile

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import ru.hse.glimpse.network.api.profile.model.Profile

interface ProfileApi {

    @GET("users/{user_id}/profile")
    suspend fun getProfile(
        @Path("user_id") userId: String,
    ): ApiResponse<Unit>

    @POST("users/{user_id}/profile")
    suspend fun sendProfile(
        @Path("user_id") userId: String,
        @Body profile: Profile,
    ): ApiResponse<Unit>
}

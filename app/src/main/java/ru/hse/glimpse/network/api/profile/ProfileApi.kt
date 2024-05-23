package ru.hse.glimpse.network.api.profile

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import ru.hse.glimpse.network.api.profile.model.FullProfileResponse
import ru.hse.glimpse.network.api.profile.model.Profile
import ru.hse.glimpse.network.api.profile.model.ProfileResponse

interface ProfileApi {

    @GET("users/{user_id}/profile")
    suspend fun getProfile(
        @Path("user_id") userId: String,
    ): ApiResponse<ProfileResponse>

    @POST("users/{user_id}/profile")
    suspend fun sendProfile(
        @Path("user_id") userId: String,
        @Body profile: Profile,
    ): ApiResponse<Unit>

    @PUT("users/{user_id}/profile")
    suspend fun updateProfile(
        @Path("user_id") userId: String,
        @Body profile: Profile,
    ): ApiResponse<Unit>

    @GET("users/{user_id}/profile/full")
    suspend fun getFullProfile(
        @Path("user_id") userId: String,
    ): ApiResponse<FullProfileResponse>

    @GET("users/{user_id}/profile/recommendation")
    suspend fun getRecommendation(
        @Path("user_id") userId: String,
    ): ApiResponse<FullProfileResponse>
}

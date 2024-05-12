package ru.hse.glimpse.network.api.profile

import com.skydoves.sandwich.ApiResponse
import ru.hse.glimpse.network.api.profile.model.Profile

class ProfileRepository (
    private val profileApi: ProfileApi,
) {

    suspend fun getProfile(
        userId: String,
    ): ApiResponse<Unit> {
        return profileApi.getProfile(userId)
    }

    suspend fun sendProfile(
        userId: String,
        profile: Profile,
    ): ApiResponse<Unit> {
        return profileApi.sendProfile(userId, profile)
    }
}

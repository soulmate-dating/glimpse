package ru.hse.glimpse.network.api.profile

import com.skydoves.sandwich.ApiResponse
import ru.hse.glimpse.network.api.profile.model.Profile
import ru.hse.glimpse.network.api.profile.model.ProfileResponse

class ProfileRepository (
    private val profileApi: ProfileApi,
) {

    suspend fun getProfile(
        userId: String,
    ): ApiResponse<ProfileResponse> {
        return profileApi.getProfile(userId)
    }

    suspend fun sendProfile(
        userId: String,
        profile: Profile,
    ): ApiResponse<Unit> {
        return profileApi.sendProfile(userId, profile)
    }
}

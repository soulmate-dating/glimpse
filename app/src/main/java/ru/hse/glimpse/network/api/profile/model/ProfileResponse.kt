package ru.hse.glimpse.network.api.profile.model

import androidx.annotation.Keep

@Keep
data class ProfileResponse(
    val data: Profile?,
    val error: String?,
)

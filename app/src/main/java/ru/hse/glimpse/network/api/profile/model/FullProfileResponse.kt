package ru.hse.glimpse.network.api.profile.model

import androidx.annotation.Keep

@Keep
data class FullProfileResponse(
    val data: FullProfile,
    val error: String? = null,
)

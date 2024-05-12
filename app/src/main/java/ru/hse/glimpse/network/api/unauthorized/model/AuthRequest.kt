package ru.hse.glimpse.network.api.unauthorized.model

import androidx.annotation.Keep

@Keep
data class AuthRequest(
    val email: String,
    val password: String,
)

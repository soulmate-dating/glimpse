package ru.hse.glimpse.network.api.chats.model

import androidx.annotation.Keep

@Keep
data class CompanionResponse(
    val companions: List<ChatSummary>,
)

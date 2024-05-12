package ru.hse.glimpse.network.api.prompts.model

import androidx.annotation.Keep

@Keep
data class PromptsResponse(
    val data: List<Prompt>,
    val error: String?,
)

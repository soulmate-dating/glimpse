package ru.hse.glimpse.network.api.prompts.model

import androidx.annotation.Keep

@Keep
data class PromptResponse(
    val data: Prompt,
    val error: String?,
)

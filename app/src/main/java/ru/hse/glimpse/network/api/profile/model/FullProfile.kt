package ru.hse.glimpse.network.api.profile.model

import androidx.annotation.Keep
import ru.hse.glimpse.network.api.prompts.model.Prompt

@Keep
data class FullProfile(
    val profile: Profile,
    val prompts: List<Prompt>,
)

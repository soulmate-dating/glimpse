package ru.hse.glimpse.screens.prompts.presentation

import ru.hse.glimpse.network.api.profile.model.FullProfile

data class PromptsState(
    val fullProfile: FullProfile? = null,
)

package ru.hse.glimpse.screens.main.presentation

import ru.hse.glimpse.network.api.profile.model.FullProfile
import ru.hse.glimpse.network.api.prompts.model.Prompt

data class MainState(
    val fullProfile: FullProfile? = null,
    val isLoading: Boolean = false,
    val isPullToRefreshRunning: Boolean = false,
    val selectedPrompt: Prompt? = null,
)

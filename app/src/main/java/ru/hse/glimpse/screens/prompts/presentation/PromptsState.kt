package ru.hse.glimpse.screens.prompts.presentation

import ru.hse.glimpse.network.api.prompts.model.Prompt

data class PromptsState(
    val prompts: List<Prompt> = emptyList(),
)

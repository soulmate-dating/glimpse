package ru.hse.glimpse.screens.prompts.presentation

import ru.hse.glimpse.network.api.prompts.model.Prompt

sealed interface PromptsCommand {
    object GetProfiles : PromptsCommand
    data class SendPrompt(
        val prompt: Prompt,
    ) : PromptsCommand
}

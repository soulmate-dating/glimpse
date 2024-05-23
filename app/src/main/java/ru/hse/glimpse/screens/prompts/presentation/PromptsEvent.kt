package ru.hse.glimpse.screens.prompts.presentation

import ru.hse.glimpse.network.api.profile.model.FullProfile
import ru.hse.glimpse.network.api.prompts.model.Prompt

sealed interface PromptsEvent

sealed interface PromptsCommandResultEvent : PromptsEvent {
    data class PostPromptSuccess(val prompt: Prompt) : PromptsCommandResultEvent
    data class PostPromptError(val message: String?) : PromptsCommandResultEvent

    data class LoadFullProfileSuccess(val fullProfile: FullProfile) : PromptsCommandResultEvent
    data class LoadFullProfileError(val message: String?) : PromptsCommandResultEvent
}

sealed interface PromptsUiEvent : PromptsEvent {
    data class SendPromptClicked(
        val prompt: Prompt,
    ) : PromptsUiEvent
    object MainClicked : PromptsUiEvent
}

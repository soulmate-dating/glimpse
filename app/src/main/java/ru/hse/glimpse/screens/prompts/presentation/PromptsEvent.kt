package ru.hse.glimpse.screens.prompts.presentation

import ru.hse.glimpse.network.api.prompts.model.Prompt

sealed interface PromptsEvent

sealed interface PromptsCommandResultEvent : PromptsEvent {
    data class GetPromptsSuccess(val prompts: List<Prompt>) : PromptsCommandResultEvent
    data class GetPromptsError(val message: String?) : PromptsCommandResultEvent

    data class PostPromptSuccess(val prompt: Prompt) : PromptsCommandResultEvent
    data class PostPromptError(val message: String?) : PromptsCommandResultEvent
}

sealed interface PromptsUiEvent : PromptsEvent {
    data class SendPromptClicked(
        val prompt: Prompt,
    ) : PromptsUiEvent
    object MainClicked : PromptsUiEvent
}

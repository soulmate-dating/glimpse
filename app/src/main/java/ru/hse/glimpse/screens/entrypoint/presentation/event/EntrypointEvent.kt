package ru.hse.glimpse.screens.entrypoint.presentation.event

import ru.hse.glimpse.network.api.prompts.model.Prompt

internal sealed interface EntrypointEvent

internal sealed interface EntrypointCommandResultEvent : EntrypointEvent {

    object AuthenticationPassed : EntrypointCommandResultEvent
    data class AuthenticationFailed(val message: String?) : EntrypointCommandResultEvent

    data class GetPromptsSuccess(val prompts: List<Prompt>) : EntrypointCommandResultEvent
    data class GetPromptsError(val message: String?) : EntrypointCommandResultEvent
}

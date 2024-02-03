package ru.hse.glimpse.screens.entrypoint.presentation.event

internal sealed interface EntrypointEvent

internal sealed interface EntrypointCommandResultEvent : EntrypointEvent {

    object AuthenticationPassed : EntrypointCommandResultEvent
    data class AuthenticationFailed(val error: Throwable) : EntrypointCommandResultEvent
}

package ru.hse.glimpse.main.presentation.event

internal sealed interface MainEvent

internal sealed interface MainCommandResultEvent : MainEvent {

    object AuthenticationPassed : MainCommandResultEvent
    data class AuthenticationFailed(val error: Throwable) : MainCommandResultEvent
}

package ru.hse.glimpse.screens.sign_up.presentation

sealed interface SignUpEvent {
    object LogInClicked : SignUpEvent
}

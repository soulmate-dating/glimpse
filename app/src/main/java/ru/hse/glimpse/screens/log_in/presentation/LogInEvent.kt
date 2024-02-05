package ru.hse.glimpse.screens.log_in.presentation

sealed interface LogInEvent {
    object SignUpClicked : LogInEvent
}

package ru.hse.glimpse.screens.log_in.presentation

sealed interface LogInEvent {
    object SignUpClicked : LogInEvent
    data class LoginClicked(val email: String, val password: String) : LogInEvent

    object LoginSuccessful : LogInEvent
    data class LoginError(val message: String?) : LogInEvent
}

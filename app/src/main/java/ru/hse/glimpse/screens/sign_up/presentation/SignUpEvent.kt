package ru.hse.glimpse.screens.sign_up.presentation

sealed interface SignUpEvent {
    object LogInClicked : SignUpEvent
    data class CreateAccountClicked(val email: String, val password: String) : SignUpEvent
}

sealed interface SignUpCommandResultEvent : SignUpEvent {
    object CreateAccountSuccess : SignUpCommandResultEvent
    data class CreateAccountFailure(val message: String) : SignUpCommandResultEvent
}

package ru.hse.glimpse.screens.sign_up.presentation

sealed interface SignUpCommand {
    data class SignUp(val email: String, val password: String) : SignUpCommand
}

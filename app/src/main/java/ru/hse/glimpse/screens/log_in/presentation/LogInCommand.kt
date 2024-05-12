package ru.hse.glimpse.screens.log_in.presentation

sealed interface LogInCommand {
    data class Login(val email: String, val password: String) : LogInCommand
    object LoadProfile : LogInCommand
    object LoadPrompts : LogInCommand
}

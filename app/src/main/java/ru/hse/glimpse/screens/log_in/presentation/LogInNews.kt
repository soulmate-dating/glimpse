package ru.hse.glimpse.screens.log_in.presentation

sealed interface LogInNews {
    object OpenSignUp : LogInNews
    object OpenMain : LogInNews
    object OpenProfile : LogInNews
    object OpenPrompts : LogInNews
    data class ShowError(val message: String?) : LogInNews
}

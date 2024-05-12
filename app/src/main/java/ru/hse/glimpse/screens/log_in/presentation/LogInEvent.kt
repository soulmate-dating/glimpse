package ru.hse.glimpse.screens.log_in.presentation

import ru.hse.glimpse.network.api.prompts.model.Prompt

sealed interface LogInEvent {
    object SignUpClicked : LogInEvent
    data class LoginClicked(val email: String, val password: String) : LogInEvent

    object LoginSuccessful : LogInEvent
    data class LoginError(val message: String?) : LogInEvent

    object ProfileLoadSuccess : LogInEvent
    data class ProfileLoadError(val message: String?) : LogInEvent

    data class PromptsLoadSuccess(val prompts: List<Prompt>) : LogInEvent
    data class PromptsLoadError(val message: String?) : LogInEvent
}

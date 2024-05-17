package ru.hse.glimpse.screens.entrypoint.presentation.news

internal sealed interface EntrypointNews {
    object OpenMainScreen : EntrypointNews
    object OpenInOrUpScreen : EntrypointNews
    data class ShowError(val message: String?) : EntrypointNews
    object OpenPromptsScreen : EntrypointNews
    object OpenFillProfileScreen : EntrypointNews
}

package ru.hse.glimpse.entrypoint.presentation.news

internal sealed interface EntrypointNews {

    data class ShowErrorToast(val message: String) : EntrypointNews
}

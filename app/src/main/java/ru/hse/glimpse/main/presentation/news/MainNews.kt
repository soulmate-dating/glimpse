package ru.hse.glimpse.main.presentation.news

internal sealed interface MainNews {

    data class ShowErrorToast(val message: String) : MainNews
}

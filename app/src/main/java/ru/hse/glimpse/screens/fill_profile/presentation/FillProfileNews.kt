package ru.hse.glimpse.screens.fill_profile.presentation

sealed interface FillProfileNews {
    object OpenMainScreen : FillProfileNews
    data class ShowError(val message: String?) : FillProfileNews
}

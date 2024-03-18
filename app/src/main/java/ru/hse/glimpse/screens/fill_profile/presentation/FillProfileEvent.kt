package ru.hse.glimpse.screens.fill_profile.presentation

sealed interface FillProfileEvent

sealed interface FillProfileUiEvent {
    object SaveClicked : FillProfileEvent
}

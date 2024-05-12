package ru.hse.glimpse.screens.fill_profile.presentation

import ru.hse.glimpse.network.api.profile.model.Profile

sealed interface FillProfileEvent {
    object FillProfileSuccess : FillProfileEvent
    data class FillProfileError(val message: String?) : FillProfileEvent
}

sealed interface FillProfileUiEvent {
    data class SaveClicked(val profile: Profile) : FillProfileEvent
}

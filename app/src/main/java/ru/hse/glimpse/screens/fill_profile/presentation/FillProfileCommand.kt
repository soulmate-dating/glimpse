package ru.hse.glimpse.screens.fill_profile.presentation

import ru.hse.glimpse.network.api.profile.model.Profile

sealed interface FillProfileCommand {
    data class SendProfile(val profile: Profile) : FillProfileCommand
}

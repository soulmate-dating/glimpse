package ru.hse.glimpse.screens.fill_profile.presentation

import ru.hse.glimpse.network.api.profile.model.Profile

sealed interface FillProfileCommand {
    object LoadProfile : FillProfileCommand
    data class SendNewProfile(val profile: Profile) : FillProfileCommand
    data class UpdateProfile(val profile: Profile) : FillProfileCommand
}

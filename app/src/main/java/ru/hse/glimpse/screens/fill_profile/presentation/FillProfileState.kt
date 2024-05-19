package ru.hse.glimpse.screens.fill_profile.presentation

import ru.hse.glimpse.network.api.profile.model.Profile

data class FillProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
)

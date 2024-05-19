package ru.hse.glimpse.screens.fill_profile.presentation

import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileCommand.SendNewProfile
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileCommand.UpdateProfile
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent.FillProfileError
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent.FillProfileSuccess
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent.LoadProfileError
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent.LoadProfileSuccess
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent.UpdateProfileError
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent.UpdateProfileSuccess
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileNews.*
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileUiEvent.SaveClicked
import ru.tinkoff.kotea.core.dsl.DslUpdate

class FillProfileUpdate :
    DslUpdate<FillProfileState, FillProfileEvent, FillProfileCommand, FillProfileNews>() {

    override fun NextBuilder.update(event: FillProfileEvent) {
        when (event) {
            is LoadProfileSuccess -> {
                state { copy(profile = event.profile, isLoading = false) }
            }

            is LoadProfileError -> Unit
            is SaveClicked -> {
                state { copy(isLoading = true) }

                if (state.profile == null) {
                    commands(SendNewProfile(event.profile))
                } else {
                    val updatedProfile = event.profile
                    if (updatedProfile != state.profile) {
                        commands(UpdateProfile(updatedProfile))
                    } else {
                        state { copy(isLoading = false) }
                        news(OpenAccountScreen)
                    }
                }
            }

            is FillProfileSuccess -> {
                state { copy(isLoading = false) }
                news(OpenMainScreen)
            }

            is UpdateProfileSuccess -> {
                state { copy(isLoading = false) }
                news(OpenAccountScreen)
            }

            is FillProfileError -> {
                state { copy(isLoading = false) }
                news(ShowError(event.message))
            }

            is UpdateProfileError -> {
                state { copy(isLoading = false) }
                news(ShowError(event.message))
            }
        }
    }
}

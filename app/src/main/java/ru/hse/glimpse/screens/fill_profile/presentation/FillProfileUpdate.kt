package ru.hse.glimpse.screens.fill_profile.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

class FillProfileUpdate : DslUpdate<FillProfileState, FillProfileEvent, FillProfileCommand, FillProfileNews>() {

    override fun NextBuilder.update(event: FillProfileEvent) {
        when (event) {
            is FillProfileUiEvent.SaveClicked -> {
                state { copy(isLoading = true) }
                commands(FillProfileCommand.SendProfile(event.profile))
            }
            is FillProfileEvent.FillProfileSuccess -> {
                state { copy(isLoading = false) }
                news(FillProfileNews.OpenMainScreen)
            }
            is FillProfileEvent.FillProfileError -> {
                state { copy(isLoading = false) }
                news(FillProfileNews.ShowError(event.message))
            }
        }
    }
}

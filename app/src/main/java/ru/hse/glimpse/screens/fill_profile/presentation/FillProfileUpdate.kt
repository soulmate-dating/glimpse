package ru.hse.glimpse.screens.fill_profile.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

class FillProfileUpdate : DslUpdate<FillProfileState, FillProfileEvent, FillProfileCommand, FillProfileNews>() {

    override fun NextBuilder.update(event: FillProfileEvent) {
        when (event) {
            is FillProfileUiEvent.SaveClicked -> news(FillProfileNews.OpenChatsScreen)
        }
    }
}

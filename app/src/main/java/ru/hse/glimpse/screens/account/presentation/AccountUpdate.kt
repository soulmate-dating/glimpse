package ru.hse.glimpse.screens.account.presentation

import ru.hse.glimpse.screens.account.presentation.AccountCommand.Logout
import ru.hse.glimpse.screens.account.presentation.AccountCommandResultEvent.ProfileLoadError
import ru.hse.glimpse.screens.account.presentation.AccountCommandResultEvent.ProfileLoadSuccess
import ru.hse.glimpse.screens.account.presentation.AccountNews.*
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.ChatsClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.LogoutClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.MainClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.ProfileClicked
import ru.hse.glimpse.screens.account.presentation.AccountUiEvent.PromptsClicked
import ru.tinkoff.kotea.core.dsl.DslUpdate

class AccountUpdate : DslUpdate<AccountState, AccountEvent, AccountCommand, AccountNews>() {

    override fun NextBuilder.update(event: AccountEvent) {
        when (event) {
            is AccountUiEvent -> handleUiEvent(event)
            is AccountCommandResultEvent -> handleCommandResultEvent(event)
        }
    }

    private fun NextBuilder.handleUiEvent(event: AccountUiEvent) {
        when (event) {
            is MainClicked -> news(OpenMain)
            is ChatsClicked -> news(OpenChats)
            is ProfileClicked -> news(OpenProfile)
            is PromptsClicked -> news(OpenPrompts)
            is LogoutClicked -> {
                commands(Logout)
                news(OpenInOrUpScreen)
            }
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: AccountCommandResultEvent) {
        when (event) {
            is ProfileLoadSuccess -> {
                val profile = event.profile
                state { copy(firstName = profile.firstName, imageUrl = profile.profilePictureUrl) }
            }
            is ProfileLoadError -> {
                state { copy(firstName = null, imageUrl = null) }
                news(ShowError(event.message))
            }
        }
    }
}

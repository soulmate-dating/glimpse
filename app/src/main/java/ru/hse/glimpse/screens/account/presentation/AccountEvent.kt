package ru.hse.glimpse.screens.account.presentation

import ru.hse.glimpse.network.api.profile.model.Profile

sealed interface AccountEvent

sealed interface AccountCommandResultEvent : AccountEvent {
    data class ProfileLoadSuccess(val profile: Profile) : AccountCommandResultEvent
    data class ProfileLoadError(val message: String?) : AccountCommandResultEvent
}

sealed interface AccountUiEvent : AccountEvent {
    object MainClicked : AccountUiEvent
    object ChatsClicked : AccountUiEvent
    object ProfileClicked : AccountUiEvent
    object PromptsClicked : AccountUiEvent
    object LogoutClicked : AccountUiEvent
}

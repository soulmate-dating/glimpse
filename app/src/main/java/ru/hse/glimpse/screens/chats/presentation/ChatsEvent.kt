package ru.hse.glimpse.screens.chats.presentation

import ru.hse.glimpse.network.api.chats.model.ChatSummary

sealed interface ChatsEvent

sealed interface ChatsCommandResultEvent : ChatsEvent {
    data class ChatsLoaded(val companions: List<ChatSummary>) : ChatsCommandResultEvent
    data class ChatsError(val throwable: Throwable) : ChatsCommandResultEvent
}

sealed interface ChatsUiEvent : ChatsEvent {
    object MainScreenClicked : ChatsUiEvent
    object AccountClicked : ChatsUiEvent
    object ReactionsScreenClicked : ChatsUiEvent
}

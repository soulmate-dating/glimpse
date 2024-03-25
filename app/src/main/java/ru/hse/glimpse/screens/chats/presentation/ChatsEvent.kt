package ru.hse.glimpse.screens.chats.presentation

import ru.hse.glimpse.screens.chats.model.Chat

sealed interface ChatsEvent

sealed interface ChatsCommandResultEvent : ChatsEvent {
    data class ChatsLoaded(val chats: List<Chat>) : ChatsCommandResultEvent
    data class ChatsError(val throwable: Throwable) : ChatsCommandResultEvent
}

sealed interface ChatsUiEvent : ChatsEvent {
    object MainScreenClicked : ChatsUiEvent
}

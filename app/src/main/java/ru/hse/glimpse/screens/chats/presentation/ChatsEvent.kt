package ru.hse.glimpse.screens.chats.presentation

import ru.hse.glimpse.screens.chats.model.Chat

sealed interface ChatsEvent {
    data class ChatsLoaded(val chats: List<Chat>) : ChatsEvent
    data class ChatsError(val throwable: Throwable) : ChatsEvent
}

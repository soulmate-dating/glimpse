package ru.hse.glimpse.screens.chats.presentation

import ru.hse.glimpse.network.api.chats.model.ChatInfo

data class ChatsState(
    val items: List<ChatInfo> = emptyList(),
    val isLoading: Boolean = false,
)

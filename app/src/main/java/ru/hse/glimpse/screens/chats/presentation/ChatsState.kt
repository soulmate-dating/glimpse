package ru.hse.glimpse.screens.chats.presentation

import ru.hse.glimpse.screens.chats.model.Chat

data class ChatsState(
    val items: List<Chat> = emptyList(),
    val isLoading: Boolean = false,
)

package ru.hse.glimpse.screens.chats.presentation

import ru.hse.glimpse.network.api.chats.model.ChatSummary

data class ChatsState(
    val items: List<ChatSummary> = emptyList(),
    val isLoading: Boolean = false,
)

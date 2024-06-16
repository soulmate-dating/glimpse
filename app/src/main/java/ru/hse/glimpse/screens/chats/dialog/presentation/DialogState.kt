package ru.hse.glimpse.screens.chats.dialog.presentation

import ru.hse.glimpse.network.api.chats.model.MessagesResponse

data class DialogState(
    val messagesResponse: MessagesResponse? = null,
    val companionId: String? = null,
)

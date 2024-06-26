package ru.hse.glimpse.screens.chats.list_of_chats.presentation

import ru.hse.glimpse.network.api.chats.model.ChatInfo

sealed interface ChatsEvent

sealed interface ChatsCommandResultEvent : ChatsEvent {
    data class ChatsLoaded(val companions: List<ChatInfo>) : ChatsCommandResultEvent
    data class ChatsError(val message: String?) : ChatsCommandResultEvent
}

sealed interface ChatsUiEvent : ChatsEvent {
    object MainScreenClicked : ChatsUiEvent
    object AccountClicked : ChatsUiEvent
    object ReactionsScreenClicked : ChatsUiEvent
    data class ChatClicked(
        val companionId: String,
        val companionName: String,
        val avatarLink: String
    ) : ChatsUiEvent
    object LoadChatsOnResume : ChatsUiEvent
}

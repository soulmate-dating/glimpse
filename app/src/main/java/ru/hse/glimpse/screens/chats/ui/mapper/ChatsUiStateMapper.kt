package ru.hse.glimpse.screens.chats.ui.mapper

import ru.hse.glimpse.screens.chats.model.Chat
import ru.hse.glimpse.screens.chats.presentation.ChatsState
import ru.hse.glimpse.screens.chats.ui.data.ChatItem
import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper

class ChatsUiStateMapper : UiStateMapper<ChatsState, ChatsUiState> {
    override fun ResourcesProvider.map(state: ChatsState): ChatsUiState {
        return ChatsUiState(
            items = state.items.map(::mapToUi),
            isLoading = state.isLoading,
        )
    }

    private fun mapToUi(chat: Chat): ChatItem {
        return ChatItem(
            imageLink = chat.imageLink,
            name = chat.name,
            lastMessage = chat.lastMessage,
        )
    }
}

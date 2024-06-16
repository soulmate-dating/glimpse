package ru.hse.glimpse.screens.chats.list_of_chats.ui.mapper

import ru.hse.glimpse.network.api.chats.model.ChatInfo
import ru.hse.glimpse.screens.chats.list_of_chats.presentation.ChatsState
import ru.hse.glimpse.screens.chats.list_of_chats.ui.data.ChatItem
import ru.hse.glimpse.screens.chats.list_of_chats.ui.data.DividerItem
import ru.hse.glimpse.screens.chats.list_of_chats.ui.data.TurnItem
import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper

class ChatsUiStateMapper : UiStateMapper<ChatsState, ChatsUiState> {
    override fun ResourcesProvider.map(state: ChatsState): ChatsUiState {
        val items = buildList {
            if (state.items.isEmpty()) return@buildList

            val yourTurnChats = state.items.filter { it.isYourTurn }
            val theirTurnAmount = state.items - yourTurnChats.toSet()

            if (yourTurnChats.isNotEmpty()) {
                add(DividerItem())
                add(TurnItem(isYourTurn = true, amountOfChats = yourTurnChats.size))
                add(DividerItem())

                yourTurnChats.forEachIndexed { index, chatSummary ->
                    add(mapToUi(chatSummary))
                    if (index != yourTurnChats.size - 1) add(DividerItem())
                }
            }

            if (theirTurnAmount.isNotEmpty()) {
                add(DividerItem())
                add(TurnItem(isYourTurn = false, amountOfChats = theirTurnAmount.size))
                add(DividerItem())

                theirTurnAmount.forEachIndexed { index, chatSummary ->
                    add(mapToUi(chatSummary))
                    if (index != theirTurnAmount.size - 1) add(DividerItem())
                }
            }
        }
        return ChatsUiState(
            items = items,
            isLoading = state.isLoading,
        )
    }

    private fun mapToUi(summary: ChatInfo): ChatItem {
        return ChatItem(
            imageLink = summary.companion.picLink,
            name = summary.companion.firstName,
            lastMessage = summary.lastMessage,
            chatInfo = summary,
        )
    }
}

package ru.hse.glimpse.screens.chats.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

class ChatsUpdate : DslUpdate<ChatsState, ChatsEvent, ChatsCommand, ChatsNews>() {
    override fun NextBuilder.update(event: ChatsEvent) {
        when (event) {
            is ChatsEvent.ChatsLoaded -> state {
                copy(
                    items = event.chats,
                    isLoading = false,
                )
            }
            else -> Unit
        }
    }
}

package ru.hse.glimpse.screens.chats.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

class ChatsUpdate : DslUpdate<ChatsState, ChatsEvent, ChatsCommand, ChatsNews>() {
    override fun NextBuilder.update(event: ChatsEvent) {
        when (event) {
            is ChatsCommandResultEvent -> handleCommandResultEvent(event)
            is ChatsUiEvent -> handleUiEvent(event)
        }
    }


    private fun NextBuilder.handleCommandResultEvent(event: ChatsCommandResultEvent) {
        when (event) {
            is ChatsCommandResultEvent.ChatsLoaded -> state {
                copy(
                    items = event.chats,
                    isLoading = false,
                )
            }
            else -> Unit
        }
    }

    private fun NextBuilder.handleUiEvent(event: ChatsUiEvent) {
        when (event) {
            is ChatsUiEvent.MainScreenClicked -> news(ChatsNews.OpenMainScreen)
        }
    }
}

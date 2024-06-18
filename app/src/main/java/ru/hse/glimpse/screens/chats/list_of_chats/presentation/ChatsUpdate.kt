package ru.hse.glimpse.screens.chats.list_of_chats.presentation

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
                    items = event.companions,
                    isLoading = false,
                )
            }
            is ChatsCommandResultEvent.ChatsError -> news(ChatsNews.ShowError(event.message))
        }
    }

    private fun NextBuilder.handleUiEvent(event: ChatsUiEvent) {
        when (event) {
            is ChatsUiEvent.MainScreenClicked -> news(ChatsNews.OpenMainScreen)
            is ChatsUiEvent.AccountClicked -> news(ChatsNews.OpenAccountScreen)
            is ChatsUiEvent.ReactionsScreenClicked -> news(ChatsNews.OpenReactionsScreen)
            is ChatsUiEvent.ChatClicked -> news(
                ChatsNews.OpenDialogScreen(
                    companionId = event.companionId,
                    companionName = event.companionName,
                    avatarLink = event.avatarLink,
                )
            )
            is ChatsUiEvent.LoadChatsOnResume -> {
                state { copy(isLoading = true) }
                commands(ChatsCommand.LoadChats)
            }
        }
    }
}

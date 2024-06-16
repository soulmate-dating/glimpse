package ru.hse.glimpse.screens.chats.dialog.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

class DialogUpdate : DslUpdate<DialogState, DialogEvent, DialogCommand, DialogNews>() {
    override fun NextBuilder.update(event: DialogEvent) {
        when (event) {
            is DialogUiEvent -> handleUiEvent(event)
            is DialogCommandResultEvent -> handleCommandResultEvent(event)
        }
    }

    private fun NextBuilder.handleUiEvent(event: DialogUiEvent) {
        when (event) {
            is DialogUiEvent.OnInit -> {
                state { copy(companionId = event.companionId) }
                commands(DialogCommand.GetMessages(companionId = event.companionId))
            }
            is DialogUiEvent.GetMessages -> {
                state { copy(companionId = event.companionId) }
                commands(DialogCommand.GetMessages(companionId = event.companionId))
            }
            is DialogUiEvent.BackClicked -> news(DialogNews.NavigateBack)
            is DialogUiEvent.SendMessage -> commands(
                DialogCommand.SendMessage(companionId = state.companionId!!, message = event.content)
            )
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: DialogCommandResultEvent) {
        when (event) {
            is DialogCommandResultEvent.GetMessagesSuccess -> {
                state { copy(messagesResponse = event.messagesResponse) }
            }
            is DialogCommandResultEvent.GetMessagesError -> news(DialogNews.ShowError(event.message))
            is DialogCommandResultEvent.SendMessagesSuccess -> commands(
                DialogCommand.GetMessages(companionId = state.companionId!!)
            )
            is DialogCommandResultEvent.SendMessagesError -> news(DialogNews.ShowError(event.message))
        }
    }
}

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
            is DialogUiEvent.OnInit -> Unit
            is DialogUiEvent.BackClicked -> news(DialogNews.NavigateBack)
        }
    }

    private fun handleCommandResultEvent(event: DialogCommandResultEvent) {
        TODO("Not yet implemented")
    }
}

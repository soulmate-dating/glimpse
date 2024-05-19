package ru.hse.glimpse.screens.main.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

class MainUpdate : DslUpdate<MainState, MainEvent, MainCommand, MainNews>() {
    override fun NextBuilder.update(event: MainEvent) {
        when (event) {
            is MainCommandResultEvent -> handleCommandResultEvent(event)
            is MainUiEvent -> handleUiEvent(event)
        }
    }


    private fun NextBuilder.handleCommandResultEvent(event: MainCommandResultEvent) {
        when (event) {
            is MainCommandResultEvent.PromptsLoaded -> state {
                copy(items = event.prompts, isLoading = false)
            }
            else -> Unit
        }
    }

    private fun NextBuilder.handleUiEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.ChatsScreenClicked -> news(MainNews.OpenChats)
            is MainUiEvent.AccountScreenClicked -> news(MainNews.OpenAccount)
        }
    }
}

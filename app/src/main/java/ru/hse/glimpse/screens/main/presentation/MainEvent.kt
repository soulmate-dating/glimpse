package ru.hse.glimpse.screens.main.presentation

import ru.hse.glimpse.screens.main.model.Prompt

sealed interface MainEvent

sealed interface MainCommandResultEvent : MainEvent {
    data class PromptsLoaded(val prompts: List<Prompt>) : MainCommandResultEvent
    data class PromptsFailed(val throwable: Throwable) : MainCommandResultEvent
}

sealed interface MainUiEvent : MainEvent {
    object ChatsScreenClicked : MainUiEvent
}

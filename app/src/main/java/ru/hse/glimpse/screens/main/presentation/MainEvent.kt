package ru.hse.glimpse.screens.main.presentation

import ru.hse.glimpse.network.api.profile.model.FullProfile
import ru.hse.glimpse.network.api.prompts.model.Prompt

sealed interface MainEvent

sealed interface MainCommandResultEvent : MainEvent {
    data class LoadRecommendationSuccess(val fullProfile: FullProfile) : MainCommandResultEvent
    data class LoadRecommendationError(val message: String?) : MainCommandResultEvent

    object SendReactionSuccess : MainCommandResultEvent
    data class SendReactionError(val message: String?) : MainCommandResultEvent
}

sealed interface MainUiEvent : MainEvent {
    object ChatsScreenClicked : MainUiEvent
    object AccountScreenClicked : MainUiEvent
    object PullToRefresh : MainUiEvent
    object CancelClicked : MainUiEvent
    object ReactionsScreenClicked : MainUiEvent
    data class PromptClicked(val prompt: Prompt) : MainUiEvent
    data class ReactionSent(val comment: String) : MainUiEvent
}

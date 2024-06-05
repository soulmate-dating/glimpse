package ru.hse.glimpse.screens.reactions.presentation

import ru.hse.glimpse.network.api.reactions.model.Reaction

sealed interface ReactionsEvent

sealed interface ReactionsUiEvent : ReactionsEvent {
    object MainScreenClicked : ReactionsUiEvent
    object ChatsScreenClicked : ReactionsUiEvent
    object AccountScreenClicked : ReactionsUiEvent
    data class SkipReaction(val reaction: Reaction) : ReactionsUiEvent
    data class ReplyOnReaction(val reaction: Reaction) : ReactionsUiEvent
    data class SendReplyOnReaction(val message: String) : ReactionsUiEvent
}

sealed interface ReactionsCommandResultEvent : ReactionsEvent {
    data class GetReactionsSuccess(val reactions: List<Reaction>) : ReactionsCommandResultEvent
    data class GetReactionsError(val message: String?) : ReactionsCommandResultEvent

    object SkipReactionSuccess : ReactionsCommandResultEvent
    data class SkipReactionError(val message: String?) : ReactionsCommandResultEvent

    object ReplySuccess : ReactionsCommandResultEvent
    data class ReplyError(val message: String?) : ReactionsCommandResultEvent
}


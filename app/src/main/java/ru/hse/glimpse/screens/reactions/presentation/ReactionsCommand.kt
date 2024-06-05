package ru.hse.glimpse.screens.reactions.presentation

sealed interface ReactionsCommand {
    object GetReactions : ReactionsCommand
    data class SkipReaction(val reactionId: String) : ReactionsCommand
    data class Reply(val reactionId: String, val message: String) : ReactionsCommand
}

package ru.hse.glimpse.screens.reactions.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

class ReactionsUpdate : DslUpdate<ReactionsState, ReactionsEvent, ReactionsCommand, ReactionsNews>() {
    override fun NextBuilder.update(event: ReactionsEvent) {
        when (event) {
            is ReactionsUiEvent -> handleUiEvent(event)
            is ReactionsCommandResultEvent -> handleCommandResultEvent(event)
        }
    }

    private fun NextBuilder.handleUiEvent(event: ReactionsUiEvent) {
        when (event) {
            is ReactionsUiEvent.MainScreenClicked -> news(ReactionsNews.OpenMain)
            is ReactionsUiEvent.ChatsScreenClicked -> news(ReactionsNews.OpenChats)
            is ReactionsUiEvent.AccountScreenClicked -> news(ReactionsNews.OpenAccount)
            is ReactionsUiEvent.SkipReaction -> {
                commands(ReactionsCommand.SkipReaction(event.reaction.id))
            }
            is ReactionsUiEvent.ReplyOnReaction -> {
                state { copy(selectedReaction = event.reaction) }
                news(ReactionsNews.OpenBottomSheet)
            }
            is ReactionsUiEvent.SendReplyOnReaction -> {
                commands(
                    ReactionsCommand.Reply(
                        reactionId = state.selectedReaction?.id ?: "",
                        message = event.message,
                    )
                )
            }
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: ReactionsCommandResultEvent) {
        when (event) {
            is ReactionsCommandResultEvent.GetReactionsSuccess -> {
                state { copy(reactions = event.reactions, isLoading = false) }
            }
            is ReactionsCommandResultEvent.GetReactionsError -> news(ReactionsNews.ShowError(event.message))
            is ReactionsCommandResultEvent.SkipReactionSuccess -> commands(ReactionsCommand.GetReactions)
            is ReactionsCommandResultEvent.SkipReactionError -> news(ReactionsNews.ShowError(event.message))
            is ReactionsCommandResultEvent.ReplySuccess -> {
                commands(ReactionsCommand.GetReactions)
                news(ReactionsNews.HideBottomSheet)
            }
            is ReactionsCommandResultEvent.ReplyError -> news(ReactionsNews.ShowError(event.message))
        }
    }
}

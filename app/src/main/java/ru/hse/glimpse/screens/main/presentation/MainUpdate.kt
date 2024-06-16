package ru.hse.glimpse.screens.main.presentation

import ru.hse.glimpse.screens.main.presentation.MainCommand.LoadRecommendation
import ru.hse.glimpse.screens.main.presentation.MainCommandResultEvent.LoadRecommendationError
import ru.hse.glimpse.screens.main.presentation.MainCommandResultEvent.LoadRecommendationSuccess
import ru.hse.glimpse.screens.main.presentation.MainCommandResultEvent.SendReactionError
import ru.hse.glimpse.screens.main.presentation.MainCommandResultEvent.SendReactionSuccess
import ru.hse.glimpse.screens.main.presentation.MainNews.HideBottomSheet
import ru.hse.glimpse.screens.main.presentation.MainNews.ShowError
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
            is LoadRecommendationSuccess -> {
                state {
                    copy(
                        fullProfile = event.fullProfile,
                        isLoading = false,
                        isPullToRefreshRunning = false,
                    )
                }
                news(MainNews.ScrollToTopOfScreen)
            }
            is LoadRecommendationError -> news(ShowError(event.message))
            is SendReactionSuccess -> news(HideBottomSheet)
            is SendReactionError -> news(ShowError(event.message))
        }
    }

    private fun NextBuilder.handleUiEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.PullToRefresh -> {
                state { copy(isPullToRefreshRunning = true, isLoading = true) }
                commands(LoadRecommendation)
            }
            is MainUiEvent.CancelClicked -> {
                state { copy(isLoading = true) }
                commands(LoadRecommendation)
            }
            is MainUiEvent.ChatsScreenClicked -> news(MainNews.OpenChats)
            is MainUiEvent.AccountScreenClicked -> news(MainNews.OpenAccount)
            is MainUiEvent.ReactionsScreenClicked -> news(MainNews.OpenReactions)
            is MainUiEvent.PromptClicked -> {
                state { copy(selectedPrompt = event.prompt) }
                news(MainNews.OpenReactionBottomSheet)
            }
            is MainUiEvent.ReactionSent -> {
                commands(
                    MainCommand.SendReaction(
                        recipientId = state.fullProfile?.profile?.userId ?: "",
                        comment = event.comment,
                        promptId = state.selectedPrompt?.id ?: "",
                    )
                )
            }
        }
    }
}

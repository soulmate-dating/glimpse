package ru.hse.glimpse.screens.reactions.ui.mapper

import ru.hse.glimpse.R
import ru.hse.glimpse.network.api.reactions.model.Reaction
import ru.hse.glimpse.screens.common.recycler.items.EmptyStateItem
import ru.hse.glimpse.screens.reactions.presentation.ReactionsState
import ru.hse.glimpse.screens.reactions.ui.recycler.ImageReactionItem
import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper

class ReactionsUiStateMapper : UiStateMapper<ReactionsState, ReactionsUiState> {
    override fun ResourcesProvider.map(state: ReactionsState): ReactionsUiState {
        val items = if (state.reactions.isEmpty()) {
            listOf(
                EmptyStateItem(
                    mainMessage = getString(R.string.no_likes_at_the_moment),
                    imageRes = R.mipmap.empty_reactions_state_image,
                )
            )
        } else {
            state.reactions.map(::mapToUi)
        }
        return ReactionsUiState(
            items = items,
            isLoading = state.isLoading,
        )
    }

    private fun mapToUi(reaction: Reaction): ImageReactionItem {
        return ImageReactionItem(
            comment = reaction.comment,
            avatarLink = reaction.sender.avatarLink,
            firstName = reaction.sender.firstName,
            reaction = reaction,
        )
    }
}

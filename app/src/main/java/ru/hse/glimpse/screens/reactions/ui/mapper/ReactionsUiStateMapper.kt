package ru.hse.glimpse.screens.reactions.ui.mapper

import ru.hse.glimpse.network.api.reactions.model.Reaction
import ru.hse.glimpse.screens.reactions.presentation.ReactionsState
import ru.hse.glimpse.screens.reactions.ui.recycler.ImageReactionItem
import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper

class ReactionsUiStateMapper : UiStateMapper<ReactionsState, ReactionsUiState> {
    override fun ResourcesProvider.map(state: ReactionsState): ReactionsUiState {
        return ReactionsUiState(
            reactions = state.reactions.map(::mapToUi),
            isLoading = state.isLoading,
        )
    }

    private fun mapToUi(reaction: Reaction): ImageReactionItem {
        return ImageReactionItem(
            comment = reaction.comment,
            avatarLink = "https://i.ytimg.com/vi/vxXiyPgpT1Y/maxresdefault.jpg"/*reaction.sender.avatarLink*/,
            firstName = reaction.sender.firstName,
            reaction = reaction,
        )
    }
}

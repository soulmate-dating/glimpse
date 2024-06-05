package ru.hse.glimpse.screens.reactions.ui.mapper

import ru.hse.glimpse.screens.reactions.ui.recycler.ImageReactionItem

data class ReactionsUiState(
    val isLoading: Boolean = false,
    val reactions: List<ImageReactionItem> = emptyList(),
)

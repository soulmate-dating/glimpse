package ru.hse.glimpse.screens.reactions.presentation

import ru.hse.glimpse.network.api.reactions.model.Reaction

data class ReactionsState(
    val isLoading: Boolean = false,
    val reactions: List<Reaction> = emptyList(),
    val selectedReaction: Reaction? = null,
)

package ru.hse.glimpse.screens.reactions.ui.mapper

import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class ReactionsUiState(
    val isLoading: Boolean = false,
    val items: List<ViewTyped> = emptyList(),
)

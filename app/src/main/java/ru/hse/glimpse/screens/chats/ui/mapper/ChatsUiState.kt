package ru.hse.glimpse.screens.chats.ui.mapper

import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class ChatsUiState(
    val items: List<ViewTyped> = emptyList(),
    val isLoading: Boolean = false,
)

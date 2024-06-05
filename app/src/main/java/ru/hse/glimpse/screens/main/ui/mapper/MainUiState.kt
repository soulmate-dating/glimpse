package ru.hse.glimpse.screens.main.ui.mapper

import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class MainUiState(
    val firstName: String? = null,
    val items: List<ViewTyped> = emptyList(),
    val isLoading: Boolean = false,
    val isPullToRefreshRunning: Boolean = false,
)

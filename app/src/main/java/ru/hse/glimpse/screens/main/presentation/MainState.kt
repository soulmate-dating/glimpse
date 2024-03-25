package ru.hse.glimpse.screens.main.presentation

import ru.hse.glimpse.screens.main.model.Prompt

data class MainState(
    val items: List<Prompt> = emptyList(),
    val isLoading: Boolean = false,
)

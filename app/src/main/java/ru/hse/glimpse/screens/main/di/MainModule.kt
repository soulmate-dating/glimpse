package ru.hse.glimpse.screens.main.di

import ru.hse.glimpse.screens.main.presentation.MainCommand
import ru.hse.glimpse.screens.main.presentation.MainState
import ru.hse.glimpse.screens.main.presentation.MainStore
import ru.hse.glimpse.screens.main.presentation.MainUpdate
import ru.hse.glimpse.screens.main.presentation.command_handlers.LoadPromptsCommandHandler
import ru.hse.glimpse.screens.main.ui.mapper.MainUiStateMapper
import ru.tinkoff.kotea.core.KoteaStore

interface MainModule {

    fun createMainStore(): MainStore
    val mainUiStateMapper: MainUiStateMapper
}

fun MainModule(): MainModule = object : MainModule {

    override fun createMainStore(): MainStore = KoteaStore(
        initialState = MainState(isLoading = true),
        initialCommands = listOf(MainCommand.LoadPrompts),
        commandsFlowHandlers = listOf(LoadPromptsCommandHandler()),
        update = MainUpdate(),
    )

    override val mainUiStateMapper: MainUiStateMapper
        get() = MainUiStateMapper()
}

package ru.hse.glimpse.main.di

import ru.hse.glimpse.main.presentation.MainStore
import ru.hse.glimpse.main.presentation.MainUpdate
import ru.hse.glimpse.main.presentation.command.MainCommand
import ru.hse.glimpse.main.presentation.command_handlers.AuthenticationCommandHandler
import ru.hse.glimpse.main.presentation.state.MainState
import ru.tinkoff.kotea.core.KoteaStore

internal interface MainModule {

    fun getMainStore(): MainStore
}

internal fun MainModule() = object : MainModule {
    override fun getMainStore(): MainStore {
        return KoteaStore(
            initialState = MainState(),
            initialCommands = listOf(MainCommand.Authenticate),
            commandsFlowHandlers = listOf(
                AuthenticationCommandHandler()
            ),
            update = MainUpdate(),
        )
    }
}

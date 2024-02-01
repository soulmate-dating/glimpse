package ru.hse.glimpse.entrypoint.di

import ru.hse.glimpse.entrypoint.presentation.EntrypointStore
import ru.hse.glimpse.entrypoint.presentation.EntrypointUpdate
import ru.hse.glimpse.entrypoint.presentation.command.EntrypointCommand
import ru.hse.glimpse.entrypoint.presentation.command_handlers.AuthenticationCommandHandler
import ru.hse.glimpse.entrypoint.presentation.state.EntrypointState
import ru.tinkoff.kotea.core.KoteaStore

internal interface EntrypointModule {

    fun getMainStore(): EntrypointStore
}

internal fun EntrypointModule() = object : EntrypointModule {
    override fun getMainStore(): EntrypointStore {
        return KoteaStore(
            initialState = EntrypointState(),
            initialCommands = listOf(EntrypointCommand.Authenticate),
            commandsFlowHandlers = listOf(
                AuthenticationCommandHandler()
            ),
            update = EntrypointUpdate(),
        )
    }
}

package ru.hse.glimpse.screens.log_in.di

import ru.hse.glimpse.screens.log_in.presentation.LogInState
import ru.hse.glimpse.screens.log_in.presentation.LogInStore
import ru.hse.glimpse.screens.log_in.presentation.LogInUpdate
import ru.tinkoff.kotea.core.KoteaStore

interface LogInModule {

    fun createLogInStore(): LogInStore
}

fun LogInModule(): LogInModule {
    return object : LogInModule {
        override fun createLogInStore(): LogInStore {
            return KoteaStore(
                initialState = LogInState(),
                initialCommands = emptyList(),
                commandsFlowHandlers = emptyList(),
                update = LogInUpdate(),
            )
        }
    }
}

package ru.hse.glimpse.screens.in_or_up.di

import ru.hse.glimpse.screens.in_or_up.presentation.InOrUpState
import ru.hse.glimpse.screens.in_or_up.presentation.InOrUpStore
import ru.hse.glimpse.screens.in_or_up.presentation.InOrUpUpdate
import ru.tinkoff.kotea.core.KoteaStore

interface InOrUpModule {

    fun getInOrUpStore(): InOrUpStore
}

fun InOrUpModule(): InOrUpModule {

    return object : InOrUpModule {
        override fun getInOrUpStore(): InOrUpStore {
            return KoteaStore(
                initialState = InOrUpState(),
                initialCommands = emptyList(),
                commandsFlowHandlers = emptyList(),
                update = InOrUpUpdate(),
            )
        }
    }
}

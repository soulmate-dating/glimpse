package ru.hse.glimpse.screens.sign_up.di

import ru.hse.glimpse.screens.sign_up.presentation.SignUpState
import ru.hse.glimpse.screens.sign_up.presentation.SignUpStore
import ru.hse.glimpse.screens.sign_up.presentation.SignUpUpdate
import ru.tinkoff.kotea.core.KoteaStore

interface SignUpModule {

    fun createSignUpStore(): SignUpStore
}

fun SignUpModule(): SignUpModule {
    return object : SignUpModule {
        override fun createSignUpStore(): SignUpStore {
            return KoteaStore(
                initialState = SignUpState(),
                initialCommands = emptyList(),
                commandsFlowHandlers = emptyList(),
                update = SignUpUpdate(),
            )
        }
    }
}

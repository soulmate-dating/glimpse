package ru.hse.glimpse.screens.fill_profile.di

import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileState
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileStore
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileUpdate
import ru.tinkoff.kotea.core.KoteaStore

interface FillProfileModule {
    fun createFillProfileStore(): FillProfileStore
}

fun FillProfileModule(): FillProfileModule {
    return object : FillProfileModule {
        override fun createFillProfileStore(): FillProfileStore {
            return KoteaStore(
                initialState = FillProfileState(),
                initialCommands = emptyList(),
                commandsFlowHandlers = emptyList(),
                update = FillProfileUpdate(),
            )
        }
    }
}

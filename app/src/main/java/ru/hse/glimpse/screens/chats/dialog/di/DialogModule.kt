package ru.hse.glimpse.screens.chats.dialog.di

import ru.hse.glimpse.screens.chats.dialog.presentation.DialogState
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogStore
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogUpdate
import ru.tinkoff.kotea.core.KoteaStore

interface DialogModule {
    fun createDialogStore(): DialogStore
}

fun DialogModule() = object : DialogModule {
    override fun createDialogStore(): DialogStore {
        return KoteaStore(
            initialState = DialogState(),
            initialCommands = emptyList(),
            commandsFlowHandlers = emptyList(),
            update = DialogUpdate(),
        )
    }
}

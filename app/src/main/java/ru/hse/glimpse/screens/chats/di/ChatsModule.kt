package ru.hse.glimpse.screens.chats.di

import ru.hse.glimpse.screens.chats.presentation.ChatsCommand
import ru.hse.glimpse.screens.chats.presentation.ChatsState
import ru.hse.glimpse.screens.chats.presentation.ChatsStore
import ru.hse.glimpse.screens.chats.presentation.ChatsUpdate
import ru.hse.glimpse.screens.chats.presentation.command_handlers.LoadChatsCommandHandler
import ru.hse.glimpse.screens.chats.ui.mapper.ChatsUiStateMapper
import ru.tinkoff.kotea.core.KoteaStore

interface ChatsModule {

    fun createChatsStore(): ChatsStore
    val uiStateMapper: ChatsUiStateMapper
}

fun ChatsModule(): ChatsModule {
    return object : ChatsModule {
        override fun createChatsStore(): ChatsStore {
            return KoteaStore(
                initialState = ChatsState(isLoading = true),
                initialCommands = listOf(ChatsCommand.LoadChats),
                commandsFlowHandlers = listOf(LoadChatsCommandHandler()),
                update = ChatsUpdate(),
            )
        }

        override val uiStateMapper: ChatsUiStateMapper
            get() = ChatsUiStateMapper()
    }
}

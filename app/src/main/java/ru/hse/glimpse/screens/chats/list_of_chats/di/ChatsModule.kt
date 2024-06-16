package ru.hse.glimpse.screens.chats.list_of_chats.di

import ru.hse.glimpse.network.api.chats.ChatsRepository
import ru.hse.glimpse.screens.chats.list_of_chats.presentation.ChatsCommand
import ru.hse.glimpse.screens.chats.list_of_chats.presentation.ChatsState
import ru.hse.glimpse.screens.chats.list_of_chats.presentation.ChatsStore
import ru.hse.glimpse.screens.chats.list_of_chats.presentation.ChatsUpdate
import ru.hse.glimpse.screens.chats.list_of_chats.presentation.command_handlers.LoadChatsCommandHandler
import ru.hse.glimpse.screens.chats.list_of_chats.ui.mapper.ChatsUiStateMapper
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.KoteaStore

interface ChatsModule {

    fun createChatsStore(): ChatsStore
    val uiStateMapper: ChatsUiStateMapper
}

fun ChatsModule(
    userInfoManager: UserInfoManager,
    chatsRepository: ChatsRepository,
): ChatsModule {
    return object : ChatsModule {
        override fun createChatsStore(): ChatsStore {
            return KoteaStore(
                initialState = ChatsState(isLoading = true),
                initialCommands = listOf(ChatsCommand.LoadChats),
                commandsFlowHandlers = listOf(
                    LoadChatsCommandHandler(userInfoManager, chatsRepository),
                ),
                update = ChatsUpdate(),
            )
        }

        override val uiStateMapper: ChatsUiStateMapper
            get() = ChatsUiStateMapper()
    }
}

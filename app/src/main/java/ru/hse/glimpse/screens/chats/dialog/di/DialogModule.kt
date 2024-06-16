package ru.hse.glimpse.screens.chats.dialog.di

import ru.hse.glimpse.network.api.chats.ChatsRepository
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogState
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogStore
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogUpdate
import ru.hse.glimpse.screens.chats.dialog.presentation.command_handlers.GetMessagesCommandHandler
import ru.hse.glimpse.screens.chats.dialog.presentation.command_handlers.SendMessageCommandHandler
import ru.hse.glimpse.screens.chats.dialog.ui.mapper.DialogUiStateMapper
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.KoteaStore

interface DialogModule {
    fun createDialogStore(): DialogStore
    val dialogUiStateMapper: DialogUiStateMapper
}

fun DialogModule(
    userInfoManager: UserInfoManager,
    chatsRepository: ChatsRepository,
) = object : DialogModule {

    override fun createDialogStore(): DialogStore {
        return KoteaStore(
            initialState = DialogState(),
            initialCommands = emptyList(),
            commandsFlowHandlers = listOf(
                GetMessagesCommandHandler(
                    userInfoManager = userInfoManager,
                    chatsRepository = chatsRepository,
                ),
                SendMessageCommandHandler(
                    userInfoManager = userInfoManager,
                    chatsRepository = chatsRepository,
                ),
            ),
            update = DialogUpdate(),
        )
    }

    override val dialogUiStateMapper: DialogUiStateMapper
        get() = DialogUiStateMapper()
}

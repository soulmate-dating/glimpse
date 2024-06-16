package ru.hse.glimpse.screens.chats.list_of_chats.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.chats.ChatsRepository
import ru.hse.glimpse.screens.chats.list_of_chats.presentation.ChatsCommand
import ru.hse.glimpse.screens.chats.list_of_chats.presentation.ChatsCommandResultEvent
import ru.hse.glimpse.screens.chats.list_of_chats.presentation.ChatsEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class LoadChatsCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val chatsRepository: ChatsRepository,
) : CommandsFlowHandler<ChatsCommand, ChatsEvent> {

    override fun handle(commands: Flow<ChatsCommand>): Flow<ChatsEvent> {
        return commands.filterIsInstance<ChatsCommand.LoadChats>().transform {
            val userId = userInfoManager.getUserId() ?: ""
            chatsRepository.getCompanions(userId)
                .suspendOnSuccess { emit(ChatsCommandResultEvent.ChatsLoaded(this.data)) }
                .suspendOnFailure { emit(ChatsCommandResultEvent.ChatsError(this.message())) }
        }
    }
}

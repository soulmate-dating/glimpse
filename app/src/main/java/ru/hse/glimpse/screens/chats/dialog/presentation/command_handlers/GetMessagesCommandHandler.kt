package ru.hse.glimpse.screens.chats.dialog.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transformLatest
import ru.hse.glimpse.network.api.chats.ChatsRepository
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogCommand
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogCommandResultEvent
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class GetMessagesCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val chatsRepository: ChatsRepository,
) : CommandsFlowHandler<DialogCommand, DialogEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<DialogCommand>): Flow<DialogEvent> {
        return commands.filterIsInstance<DialogCommand.GetMessages>().transformLatest { command ->
            val currentUserId = userInfoManager.getUserId() ?: ""
            chatsRepository.getMessages(
                currentUserId = currentUserId,
                companionId = command.companionId,
            )
                .suspendOnSuccess { emit(DialogCommandResultEvent.GetMessagesSuccess(this.data)) }
                .suspendOnFailure { emit(DialogCommandResultEvent.GetMessagesError(this.message())) }
        }
    }
}

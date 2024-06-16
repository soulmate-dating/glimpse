package ru.hse.glimpse.screens.chats.dialog.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.chats.ChatsRepository
import ru.hse.glimpse.network.api.chats.model.SendMessageBody
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogCommand
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogCommandResultEvent
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class SendMessageCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val chatsRepository: ChatsRepository,
) : CommandsFlowHandler<DialogCommand, DialogEvent> {
    override fun handle(commands: Flow<DialogCommand>): Flow<DialogEvent> {
        return commands.filterIsInstance<DialogCommand.SendMessage>().transform { command ->
            val currentUserId = userInfoManager.getUserId() ?: ""
            chatsRepository.sendMessage(
                currentUserId = currentUserId,
                sendMessageBody = SendMessageBody(
                    recipientId = command.companionId,
                    content = command.message,
                ),
            )
                .suspendOnSuccess { emit(DialogCommandResultEvent.SendMessagesSuccess) }
                .suspendOnFailure { emit(DialogCommandResultEvent.SendMessagesError(this.message())) }
        }
    }
}

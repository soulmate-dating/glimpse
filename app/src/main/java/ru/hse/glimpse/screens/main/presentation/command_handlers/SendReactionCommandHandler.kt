package ru.hse.glimpse.screens.main.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.reactions.ReactionsRepository
import ru.hse.glimpse.network.api.reactions.model.ReactionPostBody
import ru.hse.glimpse.screens.main.presentation.MainCommand
import ru.hse.glimpse.screens.main.presentation.MainCommandResultEvent
import ru.hse.glimpse.screens.main.presentation.MainEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class SendReactionCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val reactionsRepository: ReactionsRepository,
) : CommandsFlowHandler<MainCommand, MainEvent> {

    override fun handle(commands: Flow<MainCommand>): Flow<MainEvent> {
        return commands.filterIsInstance<MainCommand.SendReaction>().transform { command ->
            val userId = userInfoManager.getUserId() ?: ""
            reactionsRepository.postReaction(
                userId = userId,
                reactionPostBody = ReactionPostBody(
                    recepientId = command.recipientId,
                    comment = command.comment,
                    promptId = command.promptId,
                )
            )
                .suspendOnSuccess { emit(MainCommandResultEvent.SendReactionSuccess) }
                .suspendOnFailure { emit(MainCommandResultEvent.SendReactionError(this.message())) }
        }
    }
}

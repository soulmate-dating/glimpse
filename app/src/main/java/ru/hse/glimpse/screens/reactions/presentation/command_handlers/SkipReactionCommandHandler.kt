package ru.hse.glimpse.screens.reactions.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.reactions.ReactionsRepository
import ru.hse.glimpse.screens.reactions.presentation.ReactionsCommand
import ru.hse.glimpse.screens.reactions.presentation.ReactionsCommandResultEvent
import ru.hse.glimpse.screens.reactions.presentation.ReactionsEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class SkipReactionCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val reactionsRepository: ReactionsRepository,
) : CommandsFlowHandler<ReactionsCommand, ReactionsEvent> {

    override fun handle(commands: Flow<ReactionsCommand>): Flow<ReactionsEvent> {
        return commands.filterIsInstance<ReactionsCommand.SkipReaction>().transform {
            val userId = userInfoManager.getUserId() ?: ""
            reactionsRepository.skipReaction(userId, it.reactionId)
                .suspendOnSuccess { emit(ReactionsCommandResultEvent.SkipReactionSuccess) }
                .suspendOnFailure { emit(ReactionsCommandResultEvent.SkipReactionError(this.message())) }
        }
    }
}

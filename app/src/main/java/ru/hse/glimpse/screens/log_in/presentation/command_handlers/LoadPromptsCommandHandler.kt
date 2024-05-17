package ru.hse.glimpse.screens.log_in.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.prompts.PromptsRepository
import ru.hse.glimpse.screens.log_in.presentation.LogInCommand
import ru.hse.glimpse.screens.log_in.presentation.LogInEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class LoadPromptsCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val promptsRepository: PromptsRepository,
) : CommandsFlowHandler<LogInCommand, LogInEvent> {

    override fun handle(commands: Flow<LogInCommand>): Flow<LogInEvent> {
        return commands.filterIsInstance<LogInCommand.LoadPrompts>().transform {
            val userId = userInfoManager.getUserId() ?: ""
            promptsRepository.getPrompts(userId)
                .suspendOnSuccess { emit(LogInEvent.PromptsLoadSuccess(this.data.data)) }
                .suspendOnFailure { emit(LogInEvent.ProfileLoadError(this.message())) }
        }
    }
}

package ru.hse.glimpse.screens.entrypoint.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.prompts.PromptsRepository
import ru.hse.glimpse.screens.entrypoint.presentation.command.EntrypointCommand
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointCommandResultEvent.GetPromptsError
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointCommandResultEvent.GetPromptsSuccess
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

internal class GetPromptsCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val promptsRepository: PromptsRepository,
) : CommandsFlowHandler<EntrypointCommand, EntrypointEvent> {


    override fun handle(commands: Flow<EntrypointCommand>): Flow<EntrypointEvent> {
        return commands.filterIsInstance<EntrypointCommand.GetPrompts>()
            .transform {
                promptsRepository.getPrompts(userInfoManager.getUserId() ?: "")
                    .suspendOnSuccess {
                        emit(GetPromptsSuccess(this.data.data))
                    }.suspendOnFailure {
                        emit(GetPromptsError(this.message()))
                    }
            }
    }
}

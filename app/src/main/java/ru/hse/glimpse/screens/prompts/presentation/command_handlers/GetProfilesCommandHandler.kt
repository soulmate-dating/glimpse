package ru.hse.glimpse.screens.prompts.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.prompts.PromptsRepository
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommand
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommand.GetProfiles
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.GetPromptsError
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.GetPromptsSuccess
import ru.hse.glimpse.screens.prompts.presentation.PromptsEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class GetPromptsCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val promptsRepository: PromptsRepository,
) : CommandsFlowHandler<PromptsCommand, PromptsEvent> {

    override fun handle(commands: Flow<PromptsCommand>): Flow<PromptsEvent> {
        return commands.filterIsInstance<GetProfiles>()
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

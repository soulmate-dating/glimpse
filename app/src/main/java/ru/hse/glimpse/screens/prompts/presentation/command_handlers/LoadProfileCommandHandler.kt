package ru.hse.glimpse.screens.prompts.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommand
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.LoadFullProfileError
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.LoadFullProfileSuccess
import ru.hse.glimpse.screens.prompts.presentation.PromptsEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class LoadProfileCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val profileRepository: ProfileRepository,
) : CommandsFlowHandler<PromptsCommand, PromptsEvent> {
    override fun handle(commands: Flow<PromptsCommand>): Flow<PromptsEvent> {
        return commands.filterIsInstance<PromptsCommand.LoadFullProfile>().transform {
            val userId = userInfoManager.getUserId() ?: ""
            profileRepository.getFullProfile(userId)
                .suspendOnSuccess { emit(LoadFullProfileSuccess(this.data.data)) }
                .suspendOnFailure { emit(LoadFullProfileError(this.message())) }
        }
    }
}

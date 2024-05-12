package ru.hse.glimpse.screens.log_in.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.screens.log_in.presentation.LogInCommand
import ru.hse.glimpse.screens.log_in.presentation.LogInEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class LoadProfileCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val profileRepository: ProfileRepository,
) : CommandsFlowHandler<LogInCommand, LogInEvent> {

    override fun handle(commands: Flow<LogInCommand>): Flow<LogInEvent> {
        return commands.filterIsInstance<LogInCommand.LoadProfile>().transform {
            val userId = userInfoManager.getUserId() ?: ""
            profileRepository.getProfile(userId)
                .suspendOnSuccess { emit(LogInEvent.ProfileLoadSuccess) }
                .suspendOnFailure { emit(LogInEvent.ProfileLoadError(this.message())) }
        }
    }
}

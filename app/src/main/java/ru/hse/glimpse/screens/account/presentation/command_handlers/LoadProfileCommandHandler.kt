package ru.hse.glimpse.screens.account.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.screens.account.presentation.AccountCommand
import ru.hse.glimpse.screens.account.presentation.AccountCommand.LoadProfile
import ru.hse.glimpse.screens.account.presentation.AccountCommandResultEvent.ProfileLoadError
import ru.hse.glimpse.screens.account.presentation.AccountCommandResultEvent.ProfileLoadSuccess
import ru.hse.glimpse.screens.account.presentation.AccountEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class LoadProfileCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val profileRepository: ProfileRepository,
) : CommandsFlowHandler<AccountCommand, AccountEvent> {
    override fun handle(commands: Flow<AccountCommand>): Flow<AccountEvent> {
        return commands.filterIsInstance<LoadProfile>().transform {
            val userId = userInfoManager.getUserId() ?: ""
            profileRepository.getProfile(userId)
                .suspendOnSuccess { emit(ProfileLoadSuccess(this.data.data!!)) }
                .suspendOnFailure { emit(ProfileLoadError(this.message())) }
        }
    }
}

package ru.hse.glimpse.screens.fill_profile.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileCommand
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileCommand.UpdateProfile
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent.UpdateProfileError
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent.UpdateProfileSuccess
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class UpdateProfileCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val profileRepository: ProfileRepository,
) : CommandsFlowHandler<FillProfileCommand, FillProfileEvent> {
    override fun handle(commands: Flow<FillProfileCommand>): Flow<FillProfileEvent> {
        return commands.filterIsInstance<UpdateProfile>().transform {
            val userId = userInfoManager.getUserId() ?: ""
            profileRepository.updateProfile(userId, it.profile)
                .suspendOnSuccess { emit(UpdateProfileSuccess) }
                .suspendOnFailure { emit(UpdateProfileError(this.message())) }
        }
    }
}

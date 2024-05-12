package ru.hse.glimpse.screens.fill_profile.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileCommand
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent.FillProfileError
import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileEvent.FillProfileSuccess
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class SendProfileCommandHandler(
    private val profileRepository: ProfileRepository,
    private val userInfoManager: UserInfoManager,
) : CommandsFlowHandler<FillProfileCommand, FillProfileEvent> {


    override fun handle(commands: Flow<FillProfileCommand>): Flow<FillProfileEvent> {
        return commands.filterIsInstance<FillProfileCommand.SendProfile>()
            .transform { command ->
                profileRepository.sendProfile(
                    userId = userInfoManager.getUserId() ?: "",
                    profile = command.profile,
                ).suspendOnSuccess {
                    emit(FillProfileSuccess)
                }.suspendOnFailure {
                    emit(FillProfileError(this.message()))
                }
            }
    }
}

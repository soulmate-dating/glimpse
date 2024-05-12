package ru.hse.glimpse.screens.entrypoint.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.screens.entrypoint.presentation.command.EntrypointCommand
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointCommandResultEvent.AuthenticationFailed
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointCommandResultEvent.AuthenticationPassed
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager

internal class AuthenticationCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val profileRepository: ProfileRepository,
) : EntrypointCommandsFlowHandler {

    override fun handle(commands: Flow<EntrypointCommand>): Flow<EntrypointEvent> {
        return commands.filterIsInstance<EntrypointCommand.Authenticate>()
            .transform {
                delay(2000)

                profileRepository.getProfile(userInfoManager.getUserId() ?: "")
                    .suspendOnSuccess {
                        emit(AuthenticationPassed)
                    }.suspendOnFailure {
                        emit(AuthenticationFailed(this.message()))
                    }
            }
    }
}

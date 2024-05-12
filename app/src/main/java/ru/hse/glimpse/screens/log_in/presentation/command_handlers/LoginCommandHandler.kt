package ru.hse.glimpse.screens.log_in.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.unauthorized.AuthRepository
import ru.hse.glimpse.network.common.token.JwtTokenManager
import ru.hse.glimpse.screens.log_in.presentation.LogInCommand
import ru.hse.glimpse.screens.log_in.presentation.LogInEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class LoginCommandHandler(
    private val authRepository: AuthRepository,
    private val jwtTokenManager: JwtTokenManager,
    private val userInfoManager: UserInfoManager,
) : CommandsFlowHandler<LogInCommand, LogInEvent> {


    override fun handle(commands: Flow<LogInCommand>): Flow<LogInEvent> {
        return commands.filterIsInstance<LogInCommand.Login>()
            .transform {
                authRepository.login(it.email, it.password)
                    .suspendOnSuccess {
                        jwtTokenManager.saveAccessJwt(this.data.data.accessToken)
                        jwtTokenManager.saveRefreshJwt(this.data.data.refreshToken)
                        userInfoManager.saveUserId(this.data.data.id)

                        emit(LogInEvent.LoginSuccessful)
                    }.suspendOnFailure {
                        emit(LogInEvent.LoginError(this.message()))
                    }
            }
    }
}

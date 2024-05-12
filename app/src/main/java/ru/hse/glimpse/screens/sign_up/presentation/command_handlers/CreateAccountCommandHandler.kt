package ru.hse.glimpse.screens.sign_up.presentation.command_handlers

import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.api.unauthorized.AuthRepository
import ru.hse.glimpse.network.common.token.JwtTokenManager
import ru.hse.glimpse.screens.sign_up.presentation.SignUpCommand
import ru.hse.glimpse.screens.sign_up.presentation.SignUpCommand.SignUp
import ru.hse.glimpse.screens.sign_up.presentation.SignUpCommandResultEvent.CreateAccountFailure
import ru.hse.glimpse.screens.sign_up.presentation.SignUpCommandResultEvent.CreateAccountSuccess
import ru.hse.glimpse.screens.sign_up.presentation.SignUpEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class CreateAccountCommandHandler(
    private val authRepository: AuthRepository,
    private val jwtTokenManager: JwtTokenManager,
    private val userInfoManager: UserInfoManager,
) : CommandsFlowHandler<SignUpCommand, SignUpEvent> {

    override fun handle(commands: Flow<SignUpCommand>): Flow<SignUpEvent> {
        return commands.filterIsInstance<SignUp>()
            .transform { command ->
                authRepository.signup(command.email, command.password)
                    .suspendOnSuccess {
                        val data = this.data.data

                        jwtTokenManager.saveAccessJwt(data.accessToken)
                        jwtTokenManager.saveRefreshJwt(data.refreshToken)
                        userInfoManager.saveUserId(data.id)

                        emit(CreateAccountSuccess)
                    }
                    .suspendOnFailure {
                        emit(CreateAccountFailure(this.message()))
                    }
            }
    }
}

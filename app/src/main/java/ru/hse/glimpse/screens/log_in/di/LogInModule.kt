package ru.hse.glimpse.screens.log_in.di

import ru.hse.glimpse.network.api.unauthorized.AuthRepository
import ru.hse.glimpse.network.common.token.JwtTokenManager
import ru.hse.glimpse.screens.log_in.presentation.LogInState
import ru.hse.glimpse.screens.log_in.presentation.LogInStore
import ru.hse.glimpse.screens.log_in.presentation.LogInUpdate
import ru.hse.glimpse.screens.log_in.presentation.command_handlers.LoginCommandHandler
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.KoteaStore

interface LogInModule {

    fun createLogInStore(): LogInStore
}

fun LogInModule(
    authRepository: AuthRepository,
    jwtTokenManager: JwtTokenManager,
    userInfoManager: UserInfoManager,
): LogInModule {
    return object : LogInModule {
        override fun createLogInStore(): LogInStore {
            return KoteaStore(
                initialState = LogInState(),
                initialCommands = emptyList(),
                commandsFlowHandlers = listOf(
                    LoginCommandHandler(
                        authRepository = authRepository,
                        jwtTokenManager = jwtTokenManager,
                        userInfoManager = userInfoManager,
                    ),
                ),
                update = LogInUpdate(),
            )
        }
    }
}

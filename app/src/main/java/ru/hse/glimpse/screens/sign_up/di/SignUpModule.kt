package ru.hse.glimpse.screens.sign_up.di

import ru.hse.glimpse.network.api.unauthorized.AuthRepository
import ru.hse.glimpse.network.common.token.JwtTokenManager
import ru.hse.glimpse.screens.sign_up.presentation.SignUpState
import ru.hse.glimpse.screens.sign_up.presentation.SignUpStore
import ru.hse.glimpse.screens.sign_up.presentation.SignUpUpdate
import ru.hse.glimpse.screens.sign_up.presentation.command_handlers.CreateAccountCommandHandler
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.KoteaStore

interface SignUpModule {

    fun createSignUpStore(): SignUpStore
}

fun SignUpModule(
    authRepository: AuthRepository,
    jwtTokenManager: JwtTokenManager,
    userInfoManager: UserInfoManager,
): SignUpModule {
    return object : SignUpModule {
        override fun createSignUpStore(): SignUpStore {
            return KoteaStore(
                initialState = SignUpState(),
                initialCommands = emptyList(),
                commandsFlowHandlers = listOf(
                    CreateAccountCommandHandler(
                        authRepository = authRepository,
                        jwtTokenManager = jwtTokenManager,
                        userInfoManager = userInfoManager,
                    ),
                ),
                update = SignUpUpdate(),
            )
        }
    }
}

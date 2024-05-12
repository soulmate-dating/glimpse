package ru.hse.glimpse.screens.log_in.di

import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.network.api.prompts.PromptsRepository
import ru.hse.glimpse.network.api.unauthorized.AuthRepository
import ru.hse.glimpse.network.common.token.JwtTokenManager
import ru.hse.glimpse.screens.log_in.presentation.LogInState
import ru.hse.glimpse.screens.log_in.presentation.LogInStore
import ru.hse.glimpse.screens.log_in.presentation.LogInUpdate
import ru.hse.glimpse.screens.log_in.presentation.command_handlers.LoadProfileCommandHandler
import ru.hse.glimpse.screens.log_in.presentation.command_handlers.LoadPromptsCommandHandler
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
    profileRepository: ProfileRepository,
    promptsRepository: PromptsRepository,
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
                    LoadProfileCommandHandler(
                        userInfoManager = userInfoManager,
                        profileRepository = profileRepository,
                    ),
                    LoadPromptsCommandHandler(
                        userInfoManager = userInfoManager,
                        promptsRepository = promptsRepository,
                    )
                ),
                update = LogInUpdate(),
            )
        }
    }
}

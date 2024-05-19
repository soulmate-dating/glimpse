package ru.hse.glimpse.screens.account.di

import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.network.common.token.JwtTokenManager
import ru.hse.glimpse.screens.account.presentation.AccountCommand.LoadProfile
import ru.hse.glimpse.screens.account.presentation.AccountState
import ru.hse.glimpse.screens.account.presentation.AccountStore
import ru.hse.glimpse.screens.account.presentation.AccountUpdate
import ru.hse.glimpse.screens.account.presentation.command_handlers.LoadProfileCommandHandler
import ru.hse.glimpse.screens.account.presentation.command_handlers.LogoutCommandHandler
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.KoteaStore

interface AccountModule {
    fun createAccountStore(): AccountStore
}

fun AccountModule(
    userInfoManager: UserInfoManager,
    profileRepository: ProfileRepository,
    tokenManager: JwtTokenManager,
) = object : AccountModule {
    override fun createAccountStore(): AccountStore {
        return KoteaStore(
            initialState = AccountState(),
            initialCommands = listOf(LoadProfile),
            commandsFlowHandlers = listOf(
                LoadProfileCommandHandler(
                    userInfoManager = userInfoManager,
                    profileRepository = profileRepository,
                ),
                LogoutCommandHandler(
                    userInfoManager = userInfoManager,
                    tokenManager = tokenManager,
                ),
            ),
            update = AccountUpdate(),
        )
    }
}

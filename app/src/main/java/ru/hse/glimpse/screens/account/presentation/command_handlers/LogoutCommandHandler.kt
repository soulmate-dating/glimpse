package ru.hse.glimpse.screens.account.presentation.command_handlers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.hse.glimpse.network.common.token.JwtTokenManager
import ru.hse.glimpse.screens.account.presentation.AccountCommand
import ru.hse.glimpse.screens.account.presentation.AccountCommand.Logout
import ru.hse.glimpse.screens.account.presentation.AccountEvent
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.CommandsFlowHandler

class LogoutCommandHandler(
    private val userInfoManager: UserInfoManager,
    private val tokenManager: JwtTokenManager,
) : CommandsFlowHandler<AccountCommand, AccountEvent> {
    override fun handle(commands: Flow<AccountCommand>): Flow<AccountEvent> {
        return commands.filterIsInstance<Logout>().transform {
            userInfoManager.clearUserId()
            tokenManager.clearAllTokens()
        }
    }
}

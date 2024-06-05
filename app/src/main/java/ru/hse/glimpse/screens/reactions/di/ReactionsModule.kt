package ru.hse.glimpse.screens.reactions.di

import ru.hse.glimpse.network.api.reactions.ReactionsRepository
import ru.hse.glimpse.screens.reactions.presentation.ReactionsCommand
import ru.hse.glimpse.screens.reactions.presentation.ReactionsState
import ru.hse.glimpse.screens.reactions.presentation.ReactionsStore
import ru.hse.glimpse.screens.reactions.presentation.ReactionsUpdate
import ru.hse.glimpse.screens.reactions.presentation.command_handlers.GetReactionsCommandHandler
import ru.hse.glimpse.screens.reactions.presentation.command_handlers.ReplyCommandHandler
import ru.hse.glimpse.screens.reactions.presentation.command_handlers.SkipReactionCommandHandler
import ru.hse.glimpse.screens.reactions.ui.mapper.ReactionsUiStateMapper
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.KoteaStore

interface ReactionsModule {
    fun createReactionsStore(): ReactionsStore
    val createReactionsUiStateMapper: ReactionsUiStateMapper
}

fun ReactionsModule(
    userInfoManager: UserInfoManager,
    reactionsRepository: ReactionsRepository,
) = object : ReactionsModule {

    override fun createReactionsStore(): ReactionsStore {
        return KoteaStore(
            initialState = ReactionsState(isLoading = true),
            initialCommands = listOf(ReactionsCommand.GetReactions),
            commandsFlowHandlers = listOf(
                GetReactionsCommandHandler(
                    userInfoManager = userInfoManager,
                    reactionsRepository = reactionsRepository,
                ),
                SkipReactionCommandHandler(
                    userInfoManager = userInfoManager,
                    reactionsRepository = reactionsRepository,
                ),
                ReplyCommandHandler(
                    userInfoManager = userInfoManager,
                    reactionsRepository = reactionsRepository,
                ),
            ),
            update = ReactionsUpdate(),
        )
    }

    override val createReactionsUiStateMapper: ReactionsUiStateMapper
        get() = ReactionsUiStateMapper()
}

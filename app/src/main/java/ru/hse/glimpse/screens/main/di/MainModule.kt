package ru.hse.glimpse.screens.main.di

import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.network.api.reactions.ReactionsRepository
import ru.hse.glimpse.screens.main.presentation.MainCommand.LoadRecommendation
import ru.hse.glimpse.screens.main.presentation.MainState
import ru.hse.glimpse.screens.main.presentation.MainStore
import ru.hse.glimpse.screens.main.presentation.MainUpdate
import ru.hse.glimpse.screens.main.presentation.command_handlers.LoadRecommendationCommandHandler
import ru.hse.glimpse.screens.main.presentation.command_handlers.SendReactionCommandHandler
import ru.hse.glimpse.screens.main.ui.mapper.MainUiStateMapper
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.KoteaStore

interface MainModule {

    fun createMainStore(): MainStore
    val mainUiStateMapper: MainUiStateMapper
}

fun MainModule(
    userInfoManager: UserInfoManager,
    profileRepository: ProfileRepository,
    reactionsRepository: ReactionsRepository,
): MainModule = object : MainModule {

    override fun createMainStore(): MainStore = KoteaStore(
        initialState = MainState(isLoading = true),
        initialCommands = listOf(LoadRecommendation),
        commandsFlowHandlers = listOf(
            LoadRecommendationCommandHandler(
                userInfoManager = userInfoManager,
                profileRepository = profileRepository,
            ),
            SendReactionCommandHandler(
                userInfoManager = userInfoManager,
                reactionsRepository = reactionsRepository,
            )
        ),
        update = MainUpdate(),
    )

    override val mainUiStateMapper: MainUiStateMapper
        get() = MainUiStateMapper()
}

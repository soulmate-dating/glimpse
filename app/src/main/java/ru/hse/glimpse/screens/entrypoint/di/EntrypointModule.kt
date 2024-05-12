package ru.hse.glimpse.screens.entrypoint.di

import ru.hse.glimpse.network.api.profile.ProfileRepository
import ru.hse.glimpse.network.api.prompts.PromptsRepository
import ru.hse.glimpse.screens.entrypoint.presentation.EntrypointStore
import ru.hse.glimpse.screens.entrypoint.presentation.EntrypointUpdate
import ru.hse.glimpse.screens.entrypoint.presentation.command.EntrypointCommand
import ru.hse.glimpse.screens.entrypoint.presentation.command_handlers.AuthenticationCommandHandler
import ru.hse.glimpse.screens.entrypoint.presentation.command_handlers.GetPromptsCommandHandler
import ru.hse.glimpse.screens.entrypoint.presentation.state.EntrypointState
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.KoteaStore

internal interface EntrypointModule {

    fun getEntrypointStore(): EntrypointStore
}

internal fun EntrypointModule(
    profileRepository: ProfileRepository,
    userInfoManager: UserInfoManager,
    promptsRepository: PromptsRepository,
) = object : EntrypointModule {
    override fun getEntrypointStore(): EntrypointStore {
        return KoteaStore(
            initialState = EntrypointState(),
            initialCommands = listOf(EntrypointCommand.Authenticate),
            commandsFlowHandlers = listOf(
                AuthenticationCommandHandler(
                    profileRepository = profileRepository,
                    userInfoManager = userInfoManager
                ),
                GetPromptsCommandHandler(
                    userInfoManager = userInfoManager,
                    promptsRepository = promptsRepository,
                ),
            ),
            update = EntrypointUpdate(),
        )
    }
}

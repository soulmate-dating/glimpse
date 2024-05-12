package ru.hse.glimpse.screens.prompts.di

import ru.hse.glimpse.network.api.prompts.PromptsRepository
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommand
import ru.hse.glimpse.screens.prompts.presentation.PromptsState
import ru.hse.glimpse.screens.prompts.presentation.PromptsStore
import ru.hse.glimpse.screens.prompts.presentation.PromptsUpdate
import ru.hse.glimpse.screens.prompts.presentation.command_handlers.GetPromptsCommandHandler
import ru.hse.glimpse.screens.prompts.presentation.command_handlers.PostTextPromptCommandHandler
import ru.hse.glimpse.screens.prompts.ui.mapper.PromptsUiStateMapper
import ru.hse.glimpse.utils.user_info.UserInfoManager
import ru.tinkoff.kotea.core.KoteaStore


interface PromptsModule {
    fun createPromptsStore(): PromptsStore
    val createPromptsUiStateMapper: PromptsUiStateMapper
}

fun PromptsModule(
    userInfoManager: UserInfoManager,
    promptsRepository: PromptsRepository,
): PromptsModule = object : PromptsModule {

    override fun createPromptsStore(): PromptsStore = KoteaStore(
        initialState = PromptsState(),
        initialCommands = listOf(PromptsCommand.GetProfiles),
        commandsFlowHandlers = listOf(
            GetPromptsCommandHandler(
                userInfoManager = userInfoManager,
                promptsRepository = promptsRepository,
            ),
            PostTextPromptCommandHandler(
                userInfoManager = userInfoManager,
                promptsRepository = promptsRepository,
            ),
        ),
        update = PromptsUpdate(),
    )

    override val createPromptsUiStateMapper: PromptsUiStateMapper
        get() = PromptsUiStateMapper()
}

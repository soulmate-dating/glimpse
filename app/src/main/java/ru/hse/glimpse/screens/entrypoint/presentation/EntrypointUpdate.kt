package ru.hse.glimpse.screens.entrypoint.presentation

import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.hse.glimpse.screens.entrypoint.presentation.command.EntrypointCommand
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointCommandResultEvent
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointCommandResultEvent.AuthenticationFailed
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointCommandResultEvent.AuthenticationPassed
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointCommandResultEvent.GetPromptsError
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointCommandResultEvent.GetPromptsSuccess
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointEvent
import ru.hse.glimpse.screens.entrypoint.presentation.news.EntrypointNews
import ru.hse.glimpse.screens.entrypoint.presentation.state.EntrypointState
import ru.tinkoff.kotea.core.dsl.DslUpdate

internal class EntrypointUpdate :
    DslUpdate<EntrypointState, EntrypointEvent, EntrypointCommand, EntrypointNews>() {

    override fun NextBuilder.update(event: EntrypointEvent) {
        when (event) {
            is EntrypointCommandResultEvent -> handleCommandResultEvent(event)
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: EntrypointCommandResultEvent) {
        when (event) {
            is AuthenticationPassed -> commands(EntrypointCommand.GetPrompts)

            is AuthenticationFailed -> {
                if (event.isProfileNotFound == true) {
                    news(EntrypointNews.OpenFillProfileScreen)
                } else {
                    if (event.message != null) news(EntrypointNews.ShowError(event.message))
                    news(EntrypointNews.OpenInOrUpScreen)
                }
            }

            is GetPromptsSuccess -> {
                if (
                    event.prompts.size < 2
                    || event.prompts.count { it.type == Prompt.PromptType.TEXT } < 1
                    || event.prompts.count { it.type == Prompt.PromptType.IMAGE } < 1
                ) {
                    news(EntrypointNews.OpenPromptsScreen)
                } else {
                    news(EntrypointNews.OpenMainScreen)
                }
            }

            is GetPromptsError -> {
                news(EntrypointNews.OpenPromptsScreen)
            }
        }
    }
}

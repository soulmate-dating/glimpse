package ru.hse.glimpse.screens.entrypoint.presentation

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

internal class EntrypointUpdate : DslUpdate<EntrypointState, EntrypointEvent, EntrypointCommand, EntrypointNews>() {

    override fun NextBuilder.update(event: EntrypointEvent) {
        when (event) {
            is EntrypointCommandResultEvent -> handleCommandResultEvent(event)
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: EntrypointCommandResultEvent) {
        when (event) {
            is AuthenticationPassed -> commands(EntrypointCommand.GetProfiles)
            is AuthenticationFailed -> {
                // todo: if error code is 400, then open fill profile screen
                //  else: open OpenInOrUpScreen
                if (event.message != null) news(EntrypointNews.ShowError(event.message))
                news(EntrypointNews.OpenInOrUpScreen)
            }
            is GetPromptsSuccess -> {
                if (event.prompts.size < 2) {
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

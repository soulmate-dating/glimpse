package ru.hse.glimpse.entrypoint.presentation

import ru.hse.glimpse.entrypoint.presentation.command.EntrypointCommand
import ru.hse.glimpse.entrypoint.presentation.event.EntrypointCommandResultEvent
import ru.hse.glimpse.entrypoint.presentation.event.EntrypointCommandResultEvent.AuthenticationFailed
import ru.hse.glimpse.entrypoint.presentation.event.EntrypointCommandResultEvent.AuthenticationPassed
import ru.hse.glimpse.entrypoint.presentation.event.EntrypointEvent
import ru.hse.glimpse.entrypoint.presentation.news.EntrypointNews
import ru.hse.glimpse.entrypoint.presentation.state.EntrypointState
import ru.tinkoff.kotea.core.dsl.DslUpdate

internal class EntrypointUpdate : DslUpdate<EntrypointState, EntrypointEvent, EntrypointCommand, EntrypointNews>() {

    override fun NextBuilder.update(event: EntrypointEvent) {
        when (event) {
            is EntrypointCommandResultEvent -> handleCommandResultEvent(event)
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: EntrypointCommandResultEvent) {
        when (event) {
            is AuthenticationPassed -> Unit  // todo: emit News for navigation to Main Screen
            is AuthenticationFailed -> {
                // todo: emit News for navigation to Authentication Screen
                //  https://tinderkiller.atlassian.net/jira/software/projects/TIN/boards/1?selectedIssue=TIN-39
                news(EntrypointNews.ShowErrorToast(event.error.message ?: "Error!"))
            }
        }
    }
}

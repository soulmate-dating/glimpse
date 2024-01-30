package ru.hse.glimpse.main.presentation

import ru.hse.glimpse.main.presentation.command.MainCommand
import ru.hse.glimpse.main.presentation.event.MainCommandResultEvent
import ru.hse.glimpse.main.presentation.event.MainCommandResultEvent.AuthenticationFailed
import ru.hse.glimpse.main.presentation.event.MainCommandResultEvent.AuthenticationPassed
import ru.hse.glimpse.main.presentation.event.MainEvent
import ru.hse.glimpse.main.presentation.news.MainNews
import ru.hse.glimpse.main.presentation.state.MainState
import ru.tinkoff.kotea.core.dsl.DslUpdate

internal class MainUpdate : DslUpdate<MainState, MainEvent, MainCommand, MainNews>() {

    override fun NextBuilder.update(event: MainEvent) {
        when (event) {
            is MainCommandResultEvent -> handleCommandResultEvent(event)
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: MainCommandResultEvent) {
        when (event) {
            is AuthenticationPassed -> state { copy(textToShow = "Congratulations! Hello there!") }
            is AuthenticationFailed -> {
                state { copy(textToShow = "Some error occurred...") }
                news(MainNews.ShowErrorToast(event.error.message ?: "Error!"))
            }
        }
    }
}

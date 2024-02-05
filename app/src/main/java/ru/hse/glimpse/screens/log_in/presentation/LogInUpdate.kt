package ru.hse.glimpse.screens.log_in.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

class LogInUpdate : DslUpdate<LogInState, LogInEvent, LogInCommand, LogInNews>() {

    override fun NextBuilder.update(event: LogInEvent) {
        when (event) {
            is LogInEvent.SignUpClicked -> news(LogInNews.OpenSignUp)
        }
    }
}

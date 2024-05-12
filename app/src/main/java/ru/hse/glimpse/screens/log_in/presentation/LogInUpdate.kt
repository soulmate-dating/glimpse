package ru.hse.glimpse.screens.log_in.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

class LogInUpdate : DslUpdate<LogInState, LogInEvent, LogInCommand, LogInNews>() {

    override fun NextBuilder.update(event: LogInEvent) {
        // todo: implement auth logic when max fix back
        when (event) {
            is LogInEvent.SignUpClicked -> news(LogInNews.OpenSignUp)
            is LogInEvent.LoginClicked -> {
                commands(LogInCommand.Login(event.email, event.password))
            }
            is LogInEvent.LoginSuccessful -> news(LogInNews.OpenMain)
            is LogInEvent.LoginError -> news(LogInNews.ShowError(event.message))
        }
    }
}

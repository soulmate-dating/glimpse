package ru.hse.glimpse.screens.log_in.presentation

import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.tinkoff.kotea.core.dsl.DslUpdate

class LogInUpdate : DslUpdate<LogInState, LogInEvent, LogInCommand, LogInNews>() {

    override fun NextBuilder.update(event: LogInEvent) {
        when (event) {
            is LogInEvent.SignUpClicked -> news(LogInNews.OpenSignUp)
            is LogInEvent.LoginClicked -> {
                commands(LogInCommand.Login(event.email, event.password))
            }
            is LogInEvent.LoginSuccessful -> commands(LogInCommand.LoadProfile)
            is LogInEvent.LoginError -> news(LogInNews.ShowError(event.message))
            is LogInEvent.ProfileLoadSuccess -> commands(LogInCommand.LoadPrompts)
            is LogInEvent.ProfileLoadError -> news(LogInNews.OpenProfile)
            is LogInEvent.PromptsLoadSuccess -> {
                if (
                    event.prompts.size < 2
                    || event.prompts.count { it.type == Prompt.PromptType.TEXT } < 1
                    || event.prompts.count { it.type == Prompt.PromptType.IMAGE } < 1
                ) {
                    news(LogInNews.OpenPrompts)
                } else {
                    news(LogInNews.OpenMain)
                }
            }
            is LogInEvent.PromptsLoadError -> news(LogInNews.OpenPrompts)
        }
    }
}

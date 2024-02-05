package ru.hse.glimpse.screens.sign_up.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

class SignUpUpdate : DslUpdate<SignUpState, SignUpEvent, SignUpCommand, SignUpNews>() {

    override fun NextBuilder.update(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.LogInClicked -> news(SignUpNews.OpenLogIn)
        }
    }
}

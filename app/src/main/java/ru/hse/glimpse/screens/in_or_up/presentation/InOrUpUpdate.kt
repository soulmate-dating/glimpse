package ru.hse.glimpse.screens.in_or_up.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

internal class InOrUpUpdate : DslUpdate<InOrUpState, InOrUpEvent, InOrUpCommand, InOrUpNews>() {

    override fun NextBuilder.update(event: InOrUpEvent) {
        when (event) {
            is InOrUpEvent.SingUpClicked -> news(InOrUpNews.OpenSignUp)
            is InOrUpEvent.SignInClicked -> news(InOrUpNews.OpenSignIn)
        }
    }
}

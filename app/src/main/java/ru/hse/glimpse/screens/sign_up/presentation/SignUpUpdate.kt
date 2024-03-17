package ru.hse.glimpse.screens.sign_up.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

class SignUpUpdate : DslUpdate<SignUpState, SignUpEvent, SignUpCommand, SignUpNews>() {

    override fun NextBuilder.update(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.LogInClicked -> news(SignUpNews.OpenLogIn)
            is SignUpEvent.CreateAccountClicked -> {
                state { copy(isLoading = true) }
                commands(SignUpCommand.SignUp(event.email, event.password))
            }

            is SignUpCommandResultEvent.CreateAccountSuccess -> {
                state { copy(isLoading = false) }
                news(SignUpNews.OpenFormFilling)
            }
            is SignUpCommandResultEvent.CreateAccountFailure -> {
                state { copy(isLoading = false) }
                news(SignUpNews.ShowError(event.message))
            }
        }
    }
}

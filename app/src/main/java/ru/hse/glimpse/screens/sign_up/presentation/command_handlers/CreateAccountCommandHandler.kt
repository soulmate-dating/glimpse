package ru.hse.glimpse.screens.sign_up.presentation.command_handlers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.hse.glimpse.screens.sign_up.presentation.SignUpCommand
import ru.hse.glimpse.screens.sign_up.presentation.SignUpCommand.SignUp
import ru.hse.glimpse.screens.sign_up.presentation.SignUpCommandResultEvent
import ru.hse.glimpse.screens.sign_up.presentation.SignUpEvent
import ru.hse.glimpse.utils.coroutines.runCatchingCancellable
import ru.tinkoff.kotea.core.CommandsFlowHandler

class CreateAccountCommandHandler : CommandsFlowHandler<SignUpCommand, SignUpEvent> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<SignUpCommand>): Flow<SignUpEvent> {
        return commands.filterIsInstance<SignUp>()
            .mapLatest {
                runCatchingCancellable {
                    delay(2000)
                }
                    .map { SignUpCommandResultEvent.CreateAccountSuccess }
                    .getOrElse {
                        SignUpCommandResultEvent.CreateAccountFailure("Something went wrong")
                    }
            }
    }
}

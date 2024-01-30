package ru.hse.glimpse.main.presentation.command_handlers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.hse.common_coroutines.runCatchingCancellable
import ru.hse.glimpse.main.presentation.command.MainCommand
import ru.hse.glimpse.main.presentation.event.MainCommandResultEvent
import ru.hse.glimpse.main.presentation.event.MainEvent

// todo: add repository to fetch auth data
internal class AuthenticationCommandHandler : MainCommandsFlowHandler {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<MainCommand>): Flow<MainEvent> {
        return commands.filterIsInstance<MainCommand.Authenticate>()
            .mapLatest {
                runCatchingCancellable {
                    delay(5000)
                    error("some error")
                }
                    .map { MainCommandResultEvent.AuthenticationPassed }
                    .getOrElse { MainCommandResultEvent.AuthenticationFailed(it) }
            }
    }
}

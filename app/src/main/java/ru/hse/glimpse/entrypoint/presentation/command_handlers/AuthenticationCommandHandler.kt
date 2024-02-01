package ru.hse.glimpse.entrypoint.presentation.command_handlers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.mapLatest
import ru.hse.common_coroutines.runCatchingCancellable
import ru.hse.glimpse.entrypoint.presentation.command.EntrypointCommand
import ru.hse.glimpse.entrypoint.presentation.event.EntrypointCommandResultEvent
import ru.hse.glimpse.entrypoint.presentation.event.EntrypointEvent

// todo: add repository to fetch auth data
internal class AuthenticationCommandHandler : EntrypointCommandsFlowHandler {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<EntrypointCommand>): Flow<EntrypointEvent> {
        return commands.filterIsInstance<EntrypointCommand.Authenticate>()
            .mapLatest {
                runCatchingCancellable {
                    delay(5000)
                    error("some error")
                }
                    .map { EntrypointCommandResultEvent.AuthenticationPassed }
                    .getOrElse { EntrypointCommandResultEvent.AuthenticationFailed(it) }
            }
    }
}

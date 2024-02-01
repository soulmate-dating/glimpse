package ru.hse.glimpse.entrypoint.presentation.command_handlers

import ru.hse.glimpse.entrypoint.presentation.command.EntrypointCommand
import ru.hse.glimpse.entrypoint.presentation.event.EntrypointEvent
import ru.tinkoff.kotea.core.CommandsFlowHandler

internal typealias EntrypointCommandsFlowHandler = CommandsFlowHandler<EntrypointCommand, EntrypointEvent>

package ru.hse.glimpse.screens.entrypoint.presentation.command_handlers

import ru.hse.glimpse.screens.entrypoint.presentation.command.EntrypointCommand
import ru.hse.glimpse.screens.entrypoint.presentation.event.EntrypointEvent
import ru.tinkoff.kotea.core.CommandsFlowHandler

internal typealias EntrypointCommandsFlowHandler = CommandsFlowHandler<EntrypointCommand, EntrypointEvent>

package ru.hse.glimpse.main.presentation.command_handlers

import ru.hse.glimpse.main.presentation.command.MainCommand
import ru.hse.glimpse.main.presentation.event.MainEvent
import ru.tinkoff.kotea.core.CommandsFlowHandler

internal typealias MainCommandsFlowHandler = CommandsFlowHandler<MainCommand, MainEvent>

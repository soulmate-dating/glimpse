package ru.hse.glimpse.screens.entrypoint.presentation.command

internal sealed interface EntrypointCommand {

    object Authenticate : EntrypointCommand
    object GetPrompts : EntrypointCommand
}

package ru.hse.glimpse.entrypoint.presentation.command

internal sealed interface EntrypointCommand {

    object Authenticate : EntrypointCommand
}

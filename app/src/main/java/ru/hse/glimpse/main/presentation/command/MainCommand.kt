package ru.hse.glimpse.main.presentation.command

internal sealed interface MainCommand {

    object Authenticate : MainCommand
}

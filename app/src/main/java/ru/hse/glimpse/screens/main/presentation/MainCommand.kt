package ru.hse.glimpse.screens.main.presentation

sealed interface MainCommand {
    object LoadPrompts : MainCommand
}

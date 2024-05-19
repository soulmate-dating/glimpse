package ru.hse.glimpse.screens.account.presentation

sealed interface AccountCommand {
    object LoadProfile : AccountCommand
    object Logout : AccountCommand
}

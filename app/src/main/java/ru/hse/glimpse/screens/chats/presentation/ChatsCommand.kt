package ru.hse.glimpse.screens.chats.presentation

sealed interface ChatsCommand {
    object LoadChats : ChatsCommand
}

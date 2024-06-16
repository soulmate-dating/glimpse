package ru.hse.glimpse.screens.chats.list_of_chats.presentation

sealed interface ChatsCommand {
    object LoadChats : ChatsCommand
}

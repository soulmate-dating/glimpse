package ru.hse.glimpse.screens.chats.dialog.presentation

sealed interface DialogCommand {
    data class GetMessages(val companionId: String) : DialogCommand
    data class SendMessage(val companionId: String, val message: String) : DialogCommand
}

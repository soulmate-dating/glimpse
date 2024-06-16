package ru.hse.glimpse.screens.chats.dialog.presentation

sealed interface DialogNews {
    object NavigateBack : DialogNews
    data class ShowError(val message: String?) : DialogNews
}

package ru.hse.glimpse.screens.chats.list_of_chats.presentation

sealed interface ChatsNews {
    object OpenMainScreen : ChatsNews
    object OpenAccountScreen : ChatsNews
    object OpenReactionsScreen : ChatsNews
    data class OpenDialogScreen(
        val companionId: String,
        val companionName: String,
        val avatarLink: String
    ) : ChatsNews
    data class ShowError(val message: String?) : ChatsNews
}

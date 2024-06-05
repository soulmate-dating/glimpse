package ru.hse.glimpse.screens.chats.presentation

sealed interface ChatsNews {
    object OpenMainScreen : ChatsNews
    object OpenAccountScreen : ChatsNews
    object OpenReactionsScreen : ChatsNews
}

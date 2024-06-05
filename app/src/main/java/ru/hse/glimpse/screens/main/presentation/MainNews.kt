package ru.hse.glimpse.screens.main.presentation

sealed interface MainNews {
    object OpenChats : MainNews
    object OpenAccount : MainNews
    object OpenReactions : MainNews
    data class ShowError(val message: String?) : MainNews
    object OpenReactionBottomSheet : MainNews
    object HideBottomSheet : MainNews
}

package ru.hse.glimpse.screens.reactions.presentation

sealed interface ReactionsNews {
    object OpenMain : ReactionsNews
    object OpenChats : ReactionsNews
    object OpenAccount : ReactionsNews
    object OpenBottomSheet : ReactionsNews
    data class ShowError(val message: String?) : ReactionsNews
    object HideBottomSheet : ReactionsNews
}

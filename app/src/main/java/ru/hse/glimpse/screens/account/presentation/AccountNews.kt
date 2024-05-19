package ru.hse.glimpse.screens.account.presentation

sealed interface AccountNews {
    object OpenMain : AccountNews
    object OpenChats : AccountNews
    object OpenProfile : AccountNews
    object OpenPrompts : AccountNews
    object OpenInOrUpScreen : AccountNews
    data class ShowError(val message: String?) : AccountNews
}

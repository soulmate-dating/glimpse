package ru.hse.glimpse.screens.sign_up.presentation

sealed interface SignUpNews {

    object OpenLogIn : SignUpNews
    object OpenFormFilling : SignUpNews
    data class ShowError(val message: String) : SignUpNews
}

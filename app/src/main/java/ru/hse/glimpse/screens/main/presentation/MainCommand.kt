package ru.hse.glimpse.screens.main.presentation

sealed interface MainCommand {
    object LoadRecommendation : MainCommand
    data class SendReaction(
        val recipientId: String,
        val comment: String,
        val promptId: String,
    ) : MainCommand
}

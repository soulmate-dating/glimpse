package ru.hse.glimpse.screens.prompts.presentation

sealed interface PromptsNews {
    data class ShowError(val message: String?) : PromptsNews
    object HideBottomSheet : PromptsNews
    object OpenMainScreen : PromptsNews
}

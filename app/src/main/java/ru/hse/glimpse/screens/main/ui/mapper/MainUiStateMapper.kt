package ru.hse.glimpse.screens.main.ui.mapper

import ru.hse.glimpse.screens.main.model.Prompt
import ru.hse.glimpse.screens.main.model.Prompt.Type
import ru.hse.glimpse.screens.main.presentation.MainState
import ru.hse.glimpse.screens.common.recycler.items.ImagePromptItem
import ru.hse.glimpse.screens.common.recycler.items.TextPromptItem
import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

class MainUiStateMapper : UiStateMapper<MainState, MainUiState> {

    override fun ResourcesProvider.map(state: MainState): MainUiState {
        return MainUiState(
            items = state.items.map(::mapPromptToUiItem),
            isLoading = state.isLoading,
        )
    }

    private fun mapPromptToUiItem(prompt: Prompt): ViewTyped {
        return when (prompt.type) {
            Type.IMAGE -> getImagePromptItem(prompt)
            Type.TEXT -> getTextPromptItem(prompt)
        }
    }


    private fun getImagePromptItem(prompt: Prompt): ImagePromptItem {
        return ImagePromptItem(
            question = prompt.content,
            imageLink = prompt.imageLink,
        )
    }

    private fun getTextPromptItem(prompt: Prompt): TextPromptItem {
        return TextPromptItem(
            question = prompt.question,
            answer = prompt.content,
        )
    }
}

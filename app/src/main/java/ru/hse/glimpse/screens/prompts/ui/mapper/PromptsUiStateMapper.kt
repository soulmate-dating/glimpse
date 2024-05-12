package ru.hse.glimpse.screens.prompts.ui.mapper

import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.IMAGE
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.TEXT
import ru.hse.glimpse.screens.common.recycler.items.ImagePromptItem
import ru.hse.glimpse.screens.common.recycler.items.TextPromptItem
import ru.hse.glimpse.screens.prompts.presentation.PromptsState
import ru.hse.glimpse.screens.prompts.ui.recycler.data.EmptyPromptsTextItem
import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper

class PromptsUiStateMapper : UiStateMapper<PromptsState, PromptsUiState> {
    override fun ResourcesProvider.map(state: PromptsState): PromptsUiState {
        val items = buildList {
            if (
                state.prompts.size < 2
                || state.prompts.count { it.type == TEXT } < 1
                || state.prompts.count { it.type == IMAGE } < 1
            ) {
                add(EmptyPromptsTextItem())
            }

            state.prompts.forEach { prompt ->
                when (prompt.type) {
                    TEXT -> add(
                        TextPromptItem(
                            question = prompt.question,
                            answer = prompt.content,
                            isLikeButtonVisible = false,
                        )
                    )

                    IMAGE -> add(
                        ImagePromptItem(
                            question = prompt.question,
                            imageLink = prompt.content.replace("localhost", "10.0.2.2"),
                            isLikeButtonVisible = false,
                        )
                    )
                }
            }
        }
        return PromptsUiState(
            items = items,
        )
    }
}

package ru.hse.glimpse.screens.prompts.ui.mapper

import android.util.Log
import ru.hse.glimpse.R
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.IMAGE
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.TEXT
import ru.hse.glimpse.screens.common.recycler.items.TextPromptItem
import ru.hse.glimpse.screens.prompts.presentation.PromptsState
import ru.hse.glimpse.screens.prompts.ui.recycler.data.EmptyPromptsTextItem
import ru.hse.glimpse.screens.prompts.ui.recycler.data.HeaderItem
import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper

class PromptsUiStateMapper : UiStateMapper<PromptsState, PromptsUiState> {
    override fun ResourcesProvider.map(state: PromptsState): PromptsUiState {
        Log.d("My PromptsUiStateMapper", "PromptsUiStateMapper state: ${state.prompts}")
        val items = buildList {
            add(HeaderItem(R.string.add_your_promts))
            if (state.prompts.size < 2) add(EmptyPromptsTextItem())

            state.prompts.forEach { prompt ->
                when (prompt.type) {
                    TEXT -> add(
                        TextPromptItem(
                            question = prompt.question,
                            answer = prompt.content,
                            isLikeButtonVisible = false,
                        )
                    )
                    IMAGE -> Unit
                }
            }
        }
        Log.d("My PromptsUiStateMapper", "PromptsUiStateMapper items: $items")
        return PromptsUiState(
            items = items,
        )
    }
}

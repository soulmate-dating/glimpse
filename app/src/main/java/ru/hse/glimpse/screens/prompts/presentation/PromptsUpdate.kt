package ru.hse.glimpse.screens.prompts.presentation

import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.GetPromptsError
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.GetPromptsSuccess
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.PostPromptError
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.PostPromptSuccess
import ru.hse.glimpse.screens.prompts.presentation.PromptsNews.HideBottomSheet
import ru.hse.glimpse.screens.prompts.presentation.PromptsNews.OpenMainScreen
import ru.hse.glimpse.screens.prompts.presentation.PromptsNews.ShowError
import ru.hse.glimpse.screens.prompts.presentation.PromptsUiEvent.MainClicked
import ru.hse.glimpse.screens.prompts.presentation.PromptsUiEvent.SendPromptClicked
import ru.tinkoff.kotea.core.dsl.DslUpdate

class PromptsUpdate : DslUpdate<PromptsState, PromptsEvent, PromptsCommand, PromptsNews>() {
    override fun NextBuilder.update(event: PromptsEvent) {
        when (event) {
            is GetPromptsSuccess -> state { copy(prompts = event.prompts) }
            is PostPromptSuccess -> {
                state { copy(prompts = prompts + event.prompt) }
                news(HideBottomSheet)
            }

            is GetPromptsError -> news(ShowError(event.message))
            is PostPromptError -> news(ShowError(event.message))
            is SendPromptClicked -> commands(PromptsCommand.SendPrompt(event.prompt))
            is MainClicked -> {
                val errorText = if (state.prompts.isEmpty()) {
                    "You should add at least 2 prompts: 1 image and 1 text"
                } else if (state.prompts.count { it.type == PromptType.TEXT } < 1) {
                    "You should add at least 1 text prompt"
                } else if (state.prompts.count { it.type == PromptType.IMAGE } < 1) {
                    "You should add at least 1 image prompt"
                } else {
                    null
                }

                news(if (errorText != null) ShowError(errorText) else OpenMainScreen)
            }
        }
    }
}

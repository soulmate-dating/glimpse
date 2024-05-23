package ru.hse.glimpse.screens.prompts.presentation

import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.LoadFullProfileError
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.LoadFullProfileSuccess
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
            is LoadFullProfileSuccess -> state { copy(fullProfile = event.fullProfile) }
            is LoadFullProfileError -> news(ShowError(event.message))
            is PostPromptSuccess -> {
                state {
                    copy(
                        fullProfile = fullProfile?.copy(prompts = fullProfile.prompts + event.prompt)
                    )
                }
                news(HideBottomSheet)
            }

            is PostPromptError -> news(ShowError(event.message))
            is SendPromptClicked -> commands(PromptsCommand.SendPrompt(event.prompt))
            is MainClicked -> {
                val errorText =
                    if (state.fullProfile?.prompts == null || state.fullProfile?.prompts!!.isEmpty()) {
                        "You should add at least 2 prompts: 1 image and 1 text"
                    } else if (state.fullProfile!!.prompts.count { it.type == PromptType.TEXT } < 1) {
                        "You should add at least 1 text prompt"
                    } else if (state.fullProfile!!.prompts.count { it.type == PromptType.IMAGE } < 1) {
                        "You should add at least 1 image prompt"
                    } else {
                        null
                    }

                news(if (errorText != null) ShowError(errorText) else OpenMainScreen)
            }
        }
    }
}

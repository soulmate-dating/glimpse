package ru.hse.glimpse.screens.prompts.presentation

import android.util.Log
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.GetPromptsError
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.GetPromptsSuccess
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.PostPromptError
import ru.hse.glimpse.screens.prompts.presentation.PromptsCommandResultEvent.PostPromptSuccess
import ru.hse.glimpse.screens.prompts.presentation.PromptsNews.ShowError
import ru.hse.glimpse.screens.prompts.presentation.PromptsUiEvent.SendPromptClicked
import ru.tinkoff.kotea.core.dsl.DslUpdate

class PromptsUpdate : DslUpdate<PromptsState, PromptsEvent, PromptsCommand, PromptsNews>() {
    override fun NextBuilder.update(event: PromptsEvent) {
        Log.d("My PromptsUpdate", "Event come: $event")
        when (event) {
            is GetPromptsSuccess -> state { copy(prompts = event.prompts) }
            is PostPromptSuccess -> state { copy(prompts = prompts + event.prompt) }
            is GetPromptsError -> news(ShowError(event.message))
            is PostPromptError -> news(ShowError(event.message))
            is SendPromptClicked -> {
                commands(PromptsCommand.SendPrompt(event.prompt))
            }
        }
    }
}

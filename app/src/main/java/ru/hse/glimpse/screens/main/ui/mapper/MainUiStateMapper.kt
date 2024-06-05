package ru.hse.glimpse.screens.main.ui.mapper

import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.IMAGE
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.TEXT
import ru.hse.glimpse.screens.main.presentation.MainState
import ru.hse.glimpse.screens.common.recycler.items.ImagePromptItem
import ru.hse.glimpse.screens.common.recycler.items.ProfileFeaturesItem
import ru.hse.glimpse.screens.common.recycler.items.TextPromptItem
import ru.hse.glimpse.utils.kotlin.capitalized
import ru.hse.glimpse.utils.kotlin.toYears
import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

class MainUiStateMapper : UiStateMapper<MainState, MainUiState> {

    override fun ResourcesProvider.map(state: MainState): MainUiState {
        val items = buildList {
            getProfileFeatureItem(state)?.let { add(it) }
            addAll(state.fullProfile?.prompts?.map(::mapPromptToUiItem) ?: emptyList())
        }
        return MainUiState(
            firstName = state.fullProfile?.profile?.firstName,
            items = items,
            isLoading = state.isLoading,
            isPullToRefreshRunning = state.isPullToRefreshRunning,
        )
    }

    private fun mapPromptToUiItem(prompt: Prompt): ViewTyped {
        return when (prompt.type) {
            IMAGE -> getImagePromptItem(prompt)
            TEXT -> getTextPromptItem(prompt)
        }
    }


    private fun getImagePromptItem(prompt: Prompt): ImagePromptItem {
        return ImagePromptItem(
            question = prompt.question,
            imageLink = prompt.content,
            prompt = prompt,
        )
    }

    private fun getTextPromptItem(prompt: Prompt): TextPromptItem {
        return TextPromptItem(
            question = prompt.question,
            answer = prompt.content,
            prompt = prompt,
        )
    }

    private fun getProfileFeatureItem(state: MainState): ViewTyped? = with(state.fullProfile) {
        return this?.let {
            ProfileFeaturesItem(
                age = profile.birthDate.toYears,
                height = profile.height.toString(),
                intention = profile.intention.capitalized(),
                drinks = profile.drinksAlcohol.capitalized(),
                smokes = profile.smokes.capitalized(),
                familyPlans = profile.familyPlans.capitalized(),
                lookingFor = profile.preference.capitalized(),
            )
        }
    }
}

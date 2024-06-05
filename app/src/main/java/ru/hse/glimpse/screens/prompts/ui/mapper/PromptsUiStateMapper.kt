package ru.hse.glimpse.screens.prompts.ui.mapper

import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.IMAGE
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType.TEXT
import ru.hse.glimpse.screens.common.recycler.items.ImagePromptItem
import ru.hse.glimpse.screens.common.recycler.items.ProfileFeaturesItem
import ru.hse.glimpse.screens.common.recycler.items.TextPromptItem
import ru.hse.glimpse.screens.prompts.presentation.PromptsState
import ru.hse.glimpse.screens.prompts.ui.recycler.data.EmptyPromptsTextItem
import ru.hse.glimpse.utils.kotlin.capitalized
import ru.hse.glimpse.utils.kotlin.toYears
import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

class PromptsUiStateMapper : UiStateMapper<PromptsState, PromptsUiState> {
    override fun ResourcesProvider.map(state: PromptsState): PromptsUiState {
        val items = buildList {
            state.fullProfile?.prompts.let { prompts ->
                if (
                    prompts == null
                    || prompts.size < 2
                    || prompts.count { it.type == TEXT } < 1
                    || prompts.count { it.type == IMAGE } < 1
                ) {
                    add(EmptyPromptsTextItem())
                }
            }

            getProfileFeatureItem(state)?.let { add(it) }

            state.fullProfile?.prompts?.forEach { prompt ->
                when (prompt.type) {
                    TEXT -> add(
                        TextPromptItem(
                            question = prompt.question,
                            answer = prompt.content,
                            isLikeButtonVisible = false,
                            prompt = prompt,
                        )
                    )

                    IMAGE -> add(
                        ImagePromptItem(
                            question = prompt.question,
                            imageLink = prompt.content,
                            isLikeButtonVisible = false,
                            prompt = prompt,
                        )
                    )
                }
            }
        }
        return PromptsUiState(
            items = items,
        )
    }

    private fun getProfileFeatureItem(state: PromptsState): ViewTyped? = with(state.fullProfile) {
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

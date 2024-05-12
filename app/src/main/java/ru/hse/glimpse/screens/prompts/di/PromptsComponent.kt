package ru.hse.glimpse.screens.prompts.di

import ru.hse.glimpse.screens.prompts.presentation.PromptsStore
import ru.hse.glimpse.screens.prompts.ui.mapper.PromptsUiStateMapper
import ru.hse.glimpse.utils.di.BaseComponent

abstract class PromptsComponent : BaseComponent {
    abstract fun createPromptsStore(): PromptsStore
    abstract val createPromptsUiStateMapper: PromptsUiStateMapper
}

package ru.hse.glimpse.screens.reactions.di

import ru.hse.glimpse.screens.reactions.presentation.ReactionsStore
import ru.hse.glimpse.screens.reactions.ui.mapper.ReactionsUiStateMapper
import ru.hse.glimpse.utils.di.BaseComponent

abstract class ReactionsComponent : BaseComponent {
    abstract fun createReactionsStore(): ReactionsStore
    abstract val createReactionsUiStateMapper: ReactionsUiStateMapper
}

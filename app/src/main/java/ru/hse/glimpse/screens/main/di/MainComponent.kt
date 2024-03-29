package ru.hse.glimpse.screens.main.di

import ru.hse.glimpse.screens.main.presentation.MainStore
import ru.hse.glimpse.screens.main.ui.mapper.MainUiStateMapper
import ru.hse.glimpse.utils.di.BaseComponent

abstract class MainComponent : BaseComponent {

    abstract fun createMainStore(): MainStore
    abstract val mainUiStateMapper: MainUiStateMapper
}

package ru.hse.glimpse.screens.entrypoint.di

import ru.hse.glimpse.screens.entrypoint.presentation.EntrypointStore
import ru.hse.glimpse.utils.di.BaseComponent

abstract class EntrypointComponent: BaseComponent {

    internal abstract fun getEntrypointStore(): EntrypointStore
}

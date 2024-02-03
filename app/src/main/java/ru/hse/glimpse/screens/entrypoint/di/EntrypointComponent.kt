package ru.hse.glimpse.screens.entrypoint.di

import ru.hse.glimpse.screens.entrypoint.presentation.EntrypointStore

abstract class EntrypointComponent {

    internal abstract fun getEntrypointStore(): EntrypointStore
}

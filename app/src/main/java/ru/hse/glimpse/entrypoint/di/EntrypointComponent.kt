package ru.hse.glimpse.entrypoint.di

import com.github.terrakok.cicerone.Router
import ru.hse.common_navigation.NavigationDependencies
import ru.hse.common_navigation.NavigationScreenModule
import ru.hse.glimpse.entrypoint.presentation.EntrypointStore

abstract class EntrypointComponent : NavigationDependencies {

    internal abstract fun getMainStore(): EntrypointStore

    abstract override val router: Router
}

internal fun EntrypointComponent(
    navigationScreenModule: NavigationScreenModule,
): EntrypointComponent {
    return object : EntrypointComponent(),
        EntrypointModule by EntrypointModule(),
        NavigationScreenModule by navigationScreenModule {}
}

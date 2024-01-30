package ru.hse.glimpse.main.di

import com.github.terrakok.cicerone.Router
import ru.hse.common_navigation.NavigationDependencies
import ru.hse.common_navigation.NavigationScreenModule
import ru.hse.glimpse.main.presentation.MainStore

abstract class MainComponent : NavigationDependencies {

    internal abstract fun getMainStore(): MainStore

    abstract override val router: Router
}

internal fun MainComponent(
    navigationScreenModule: NavigationScreenModule,
): MainComponent {
    return object : MainComponent(),
        MainModule by MainModule(),
        NavigationScreenModule by navigationScreenModule {}
}

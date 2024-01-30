package ru.hse.common_navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import kotlin.LazyThreadSafetyMode.NONE

interface NavigationScreenModule {

    val router: Router

    val navigatorHolder: NavigatorHolder
}

fun NavigationScreenModule(): NavigationScreenModule {
    return object : NavigationScreenModule {

        private val cicerone: Cicerone<Router> by lazy(NONE) { Cicerone.create(router) }

        override val router: Router by lazy(NONE) { Router() }

        override val navigatorHolder: NavigatorHolder by lazy(NONE) { cicerone.getNavigatorHolder() }
    }
}

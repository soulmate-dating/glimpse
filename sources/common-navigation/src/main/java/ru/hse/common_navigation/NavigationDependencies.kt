package ru.hse.common_navigation

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

interface NavigationDependencies {

    val router: Router

    val navigatorHolder: NavigatorHolder
}

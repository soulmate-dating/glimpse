package ru.hse.glimpse.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.hse.glimpse.screens.entrypoint.EntrypointFragment
import ru.hse.glimpse.screens.in_or_up.InOrUpFragment

object Screens {

    fun EntrypointScreen() = FragmentScreen { EntrypointFragment() }

    fun InOrUpScreen() = FragmentScreen { InOrUpFragment() }
}

package ru.hse.glimpse.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.hse.glimpse.screens.in_or_up.InOrUpFragment
import ru.hse.glimpse.screens.sign_up.SignUpFragment

@Suppress("FunctionName")
object Screens {

    fun InOrUpScreen() = FragmentScreen { InOrUpFragment() }

    fun SignUpScreen() = FragmentScreen { SignUpFragment() }
}

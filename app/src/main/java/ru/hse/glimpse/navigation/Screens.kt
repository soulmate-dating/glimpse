package ru.hse.glimpse.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.hse.glimpse.screens.account.AccountFragment
import ru.hse.glimpse.screens.chats.ChatsFragment
import ru.hse.glimpse.screens.fill_profile.FillProfileFragment
import ru.hse.glimpse.screens.in_or_up.InOrUpFragment
import ru.hse.glimpse.screens.log_in.LogInFragment
import ru.hse.glimpse.screens.main.MainFragment
import ru.hse.glimpse.screens.prompts.PromptsFragment
import ru.hse.glimpse.screens.reactions.ReactionsFragment
import ru.hse.glimpse.screens.sign_up.SignUpFragment

@Suppress("FunctionName")
object Screens {

    fun InOrUpScreen() = FragmentScreen { InOrUpFragment() }

    fun SignUpScreen() = FragmentScreen { SignUpFragment() }

    fun LogInScreen() = FragmentScreen { LogInFragment() }

    fun FillProfileScreen() = FragmentScreen { FillProfileFragment() }

    fun ChatsScreen() = FragmentScreen { ChatsFragment() }

    fun MainScreen() = FragmentScreen { MainFragment() }

    fun PromptsScreen() = FragmentScreen { PromptsFragment() }

    fun AccountScreen() = FragmentScreen { AccountFragment() }

    fun ReactionsScreen() = FragmentScreen { ReactionsFragment() }
}

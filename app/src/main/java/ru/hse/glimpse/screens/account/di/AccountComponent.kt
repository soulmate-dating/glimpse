package ru.hse.glimpse.screens.account.di

import ru.hse.glimpse.screens.account.presentation.AccountStore
import ru.hse.glimpse.utils.di.BaseComponent

abstract class AccountComponent : BaseComponent {
    abstract fun createAccountStore(): AccountStore
}

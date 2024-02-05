package ru.hse.glimpse.screens.sign_up.di

import ru.hse.glimpse.screens.sign_up.presentation.SignUpStore
import ru.hse.glimpse.utils.di.BaseComponent

abstract class SignUpComponent : BaseComponent {

    abstract fun createSignUpStore(): SignUpStore
}

package ru.hse.glimpse.screens.log_in.di

import ru.hse.glimpse.screens.log_in.presentation.LogInStore
import ru.hse.glimpse.utils.di.BaseComponent

abstract class LogInComponent : BaseComponent {

    abstract fun createLogInStore(): LogInStore
}

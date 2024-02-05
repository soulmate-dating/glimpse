package ru.hse.glimpse.screens.in_or_up.di

import ru.hse.glimpse.screens.in_or_up.presentation.InOrUpStore
import ru.hse.glimpse.utils.di.BaseComponent

abstract class InOrUpComponent: BaseComponent {

    abstract fun getInOrUpStore(): InOrUpStore
}

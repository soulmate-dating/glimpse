package ru.hse.glimpse.screens.fill_profile.di

import ru.hse.glimpse.screens.fill_profile.presentation.FillProfileStore
import ru.hse.glimpse.utils.di.BaseComponent

abstract class FillProfileComponent : BaseComponent {
    abstract fun createFillProfileStore(): FillProfileStore
}

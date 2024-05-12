package ru.hse.glimpse.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.hse.glimpse.utils.user_info.UserInfoDataStore
import ru.hse.glimpse.utils.user_info.UserInfoManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserInfoModule {

    @Provides
    @Singleton
    fun provideUserInfoManager(
        dataStore: DataStore<Preferences>,
    ): UserInfoManager {
        return UserInfoDataStore(dataStore)
    }
}

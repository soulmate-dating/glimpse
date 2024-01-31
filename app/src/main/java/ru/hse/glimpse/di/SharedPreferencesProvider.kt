package ru.hse.glimpse.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal class SharedPreferencesProvider {

    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.applicationContext.getSharedPreferences("prefs", MODE_PRIVATE)
    }
}

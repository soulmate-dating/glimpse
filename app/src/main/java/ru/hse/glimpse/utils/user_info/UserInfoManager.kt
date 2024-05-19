package ru.hse.glimpse.utils.user_info

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UserInfoManager {
    suspend fun saveUserId(id: String)
    suspend fun getUserId(): String?
    suspend fun clearUserId()
}

class UserInfoDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : UserInfoManager {


    companion object {
        val ID_KEY = stringPreferencesKey("user_id")
    }

    override suspend fun saveUserId(id: String) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[ID_KEY] = id
        }
    }

    override suspend fun getUserId(): String? {
        return dataStore.data.map { preferences ->
            preferences[ID_KEY]
        }.first()
    }

    override suspend fun clearUserId() {
        dataStore.edit { it.remove(ID_KEY) }
    }
}

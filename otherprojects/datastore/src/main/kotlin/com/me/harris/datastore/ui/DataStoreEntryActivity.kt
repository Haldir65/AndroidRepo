package com.me.harris.datastore.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.me.harris.datastore.logic.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// https://hackernoon.com/jetpack-datastore-in-android-explained
class DataStoreEntryActivity:AppCompatActivity()
{
    companion object {
        val IS_DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    enum class UiMode {
        LIGHT, DARK
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    val uiModeFlow: Flow<UiMode> = dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preference ->
            // No type safety.
            when (preference[IS_DARK_MODE] ?: false) {
                true -> UiMode.DARK
                false -> UiMode.LIGHT
            }
        }


    suspend fun setUiMode(uiMode: UiMode) {
        dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = when (uiMode) {
                UiMode.LIGHT -> false
                UiMode.DARK -> true
            }
        }
    }



}
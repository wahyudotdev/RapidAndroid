package com.rapidcore.core.extension

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.rapidcore.core.utils.Config

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Config.PreferenceKey.DATA_STORE_APP_NAME)
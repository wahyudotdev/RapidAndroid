package com.rapidcore.core.extension

import android.content.Context
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.rapidcore.core.utils.Config

@Suppress("unused")
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Config.PreferenceKey.DATA_STORE_APP_NAME)

@Suppress("unused")
fun Context.tos(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
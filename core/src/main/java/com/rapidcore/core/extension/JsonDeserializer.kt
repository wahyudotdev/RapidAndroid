package com.rapidcore.core.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by @yzzzd on 4/22/18.
 */
@Suppress("unused")
inline fun <reified T> JSONObject.toObject(gson: Gson): T {
    return gson.fromJson(this.toString(), T::class.java)
}

@Suppress("unused")
inline fun <reified T> String.toObject(gson: Gson): T {
    return gson.fromJson(this, T::class.java)
}

@Suppress("unused")
inline fun <reified T> T.toJson(gson: Gson): String {
    return gson.toJson(this)
}

@Suppress("unused")
inline fun <reified T> JSONArray.toList(gson: Gson): List<T> {
    return gson.fromJson(this.toString(), object : TypeToken<ArrayList<T>?>() {}.type)
}

@Suppress("unused")
inline fun <reified T> String.toList(gson: Gson): List<T> {
    return gson.fromJson(this, object : TypeToken<ArrayList<T>?>() {}.type)
}

package com.example.rssreader

import android.content.Context
import android.content.SharedPreferences
import java.lang.reflect.Type

class MyPreferences @JvmOverloads constructor(context: Context, preferenceFile: String =
    SHARED_PREFS){
    private val prefs = context.getSharedPreferences(preferenceFile, Context.MODE_PRIVATE)

    fun <T> readValue(name: String, default: T): T {
        with(prefs) {
            val result: Any = when (default) {
                is Long -> getLong(name, default)
                is String -> getString(name, default)
                is Int -> getInt(name, default)
                is Boolean -> getBoolean(name, default)
                is Float -> getFloat(name, default)
                else -> {
                    throw IllegalArgumentException("This value cannot be read this way, try " +
                            "reading it with SharePreference.readNonPrimitiveValue")
                }
            }!!
            @Suppress("UNCHECKED_CAST")
            return result as T
        }
    }

    fun <T> readNonPrimitiveValue(name: String, default: T, type: Type): String? {
        with(prefs) {
            return getString(name, "")
        }
    }

    fun <T> saveValue(name: String, value: T) {
        with(prefs.edit()) {
            when (value) {
                is Long -> putLong(name, value)
                is String -> putString(name, value)
                is Int -> putInt(name, value)
                is Boolean -> putBoolean(name, value)
                is Float -> putFloat(name, value)
                else -> putString(name, value.toString())
            }
            apply()
        }
    }
    fun removeValue(name: String) {
        prefs.edit().remove(name).apply()
    }

    companion object {
        const val SHARED_PREFS = "Prefs"
    }
    }
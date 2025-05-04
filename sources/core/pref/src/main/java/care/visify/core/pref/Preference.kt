package care.visify.core.pref

import android.content.SharedPreferences
import androidx.core.content.edit

sealed interface Preference<T> {
    fun get(): T
    fun put(value: T)
    fun putForce(value: T)
    fun hasValue(): Boolean

    fun clear()

    class IntPreference(
        private val key: String,
        private val sharedPreferences: SharedPreferences
    ) : Preference<Int> {
        override fun get(): Int = sharedPreferences.getInt(key, -1)

        override fun hasValue(): Boolean = sharedPreferences.contains(key)

        override fun put(value: Int) = sharedPreferences.edit {
            putInt(key, value)
        }

        override fun putForce(value: Int) = sharedPreferences.edit(commit = true) {
            putInt(key, value)
        }

        override fun clear() = sharedPreferences.edit { remove(key) }
    }

    class StringPreference(
        private val key: String,
        private val sharedPreferences: SharedPreferences
    ) : Preference<String> {
        override fun get(): String = sharedPreferences.getString(key, "") as String

        override fun hasValue(): Boolean = sharedPreferences.contains(key)

        override fun put(value: String) = sharedPreferences.edit {
            putString(key, value)
        }

        override fun putForce(value: String) = sharedPreferences.edit(commit = true) {
            putString(key, value)
        }

        override fun clear() = sharedPreferences.edit { remove(key) }
    }

    class BooleanPreference(
        private val key: String,
        private val sharedPreferences: SharedPreferences
    ) : Preference<Boolean> {
        override fun get(): Boolean = sharedPreferences.getBoolean(key, false)

        override fun hasValue(): Boolean = sharedPreferences.contains(key)

        override fun put(value: Boolean) = sharedPreferences.edit {
            putBoolean(key, value)
        }

        override fun putForce(value: Boolean) = sharedPreferences.edit(commit = true) {
            putBoolean(key, value)
        }

        override fun clear() = sharedPreferences.edit { remove(key) }
    }
}
package care.visify.core.pref

import android.annotation.SuppressLint
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


interface PreferencesFactory {
    fun intPreference(key: String): Preference<Int>
    fun stringPreference(key: String): Preference<String>
    fun booleanPreference(key: String): Preference<Boolean>
    fun clearAll()
}

class PreferencesFactoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : PreferencesFactory {

    private val preferencesInternal
        by lazy { context.getSharedPreferences(GLOBAL_PREFS, Context.MODE_PRIVATE) }

    override fun intPreference(key: String): Preference<Int> = Preference.IntPreference(key, preferencesInternal)

    override fun stringPreference(key: String): Preference<String> = Preference.StringPreference(key, preferencesInternal)

    override fun booleanPreference(key: String): Preference<Boolean> = Preference.BooleanPreference(key, preferencesInternal)

    @SuppressLint("ApplySharedPref")
    override fun clearAll() { preferencesInternal.edit().clear().commit() }

    companion object {
        private const val GLOBAL_PREFS = "client.visify.global"
    }
}
package care.visify.core.api.http.auth

import care.visify.core.pref.PreferencesFactory
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

interface AuthTokenManager {
    fun setAccess(access: String)
    fun access(): String
    fun setRefresh(refresh: String)
    fun refresh(): String

    fun clearAuthData()
}

internal class AuthTokenManagerImpl(
    preferencesFactory: PreferencesFactory,
) : AuthTokenManager {

    private val accessRwLock = ReentrantReadWriteLock()
    private val refreshRwLock = ReentrantReadWriteLock()

    private val accessPreference = preferencesFactory.stringPreference(ACCESS_KEY)
    private val refreshPreference = preferencesFactory.stringPreference(REFRESH_KEY)

    @Volatile
    private var cachedAccess: String = accessPreference.get()

    @Volatile
    private var cachedRefresh: String = refreshPreference.get()

    override fun access(): String {
        return accessRwLock.read { cachedAccess }
    }

    override fun refresh(): String {
        return refreshRwLock.read { cachedRefresh }
    }

    override fun setAccess(access: String) {
        accessRwLock.write {
            cachedAccess = access
            accessPreference.put(access)
        }
    }

    override fun setRefresh(refresh: String) {
        refreshRwLock.write {
            cachedRefresh = refresh
            refreshPreference.put(refresh)
        }
    }

    override fun clearAuthData() {
        accessRwLock.write {
            refreshRwLock.write {
                accessPreference.clear()
                refreshPreference.clear()
                cachedAccess = EMPTY_KEY
                cachedRefresh = EMPTY_KEY
            }
        }
    }

    private companion object {
        const val ACCESS_KEY = "client.net.token.access"
        const val REFRESH_KEY = "client.net.token.refresh"
        private const val EMPTY_KEY = ""
    }
}
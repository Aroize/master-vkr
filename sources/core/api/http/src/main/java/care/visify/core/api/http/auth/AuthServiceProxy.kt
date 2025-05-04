package care.visify.core.api.http.auth

import android.util.Log
import care.visify.core.api.http.isAuthException
import care.visify.core.api.http.toBearer
import care.visify.core.bus.EventBus
import care.visify.core.bus.OnAuthFailed
import care.visify.core.ktx.CoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.plus
import okhttp3.Request
import java.util.concurrent.atomic.AtomicBoolean


class AuthServiceProxy(
    dispatchers: CoroutineDispatchers,
    private val authRefreshService: AuthRefreshService,
    private val tokenManager: AuthTokenManager,
    private val appAuthListener: AppAuthListener,
    private val forceAuthFilter: ForceAuthFilter,
    private val bus: EventBus,
) {

    private val scope = CoroutineScope(dispatchers.io) + Job()

    @Volatile
    private var actualJob: Deferred<Boolean>? = null
    private val isJobRunning = AtomicBoolean(false)

    /**
     * @return is update token succeed
     */
    suspend fun updateTokens(request: Request): Boolean {
        if (isJobRunning.compareAndSet(false, true)) {
            actualJob = scope.async { updateTokensInternal(request) }
        }
        return actualJob?.await() ?: false
    }

    private suspend fun updateTokensInternal(request: Request): Boolean {
        val currentAccess = tokenManager.access()
        val currentRefresh = tokenManager.refresh()
        val result = runCatching {
            authRefreshService.refresh(
                currentAccess.toBearer(),
                currentRefresh.toBearer()
            )
        }.onFailure { exc ->
            Log.e("Network", "Failure during refresh", exc)
            when {
                exc.isAuthException -> {
                    if (forceAuthFilter.isNeedToAuth(request)) {
                        appAuthListener.onAuthFailed()
                    }
                    bus.publish(OnAuthFailed)
                }
            }
        }.onSuccess { response ->
            tokenManager.setAccess(response.accessToken)
            tokenManager.setRefresh(response.refreshToken)
        }
        actualJob = null
        isJobRunning.compareAndSet(true, false)
        return result.isSuccess
    }
}
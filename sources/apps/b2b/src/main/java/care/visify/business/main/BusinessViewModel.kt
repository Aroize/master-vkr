package care.visify.business.main

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.viewModelScope
import care.visify.business.main.state.BusinessEffect
import care.visify.business.main.state.BusinessIntent
import care.visify.business.main.state.BusinessState
import care.visify.core.arch.VisifyViewModel
import care.visify.core.navigator.api.VisifyRouter
import care.visify.core.notifications.intent.IntentHandler
import care.visify.feature.auth.api.CurrentUserHolder
import care.visify.feature.auth.api.screens.EmailOtpScreen
import care.visify.feature.dashboard.api.screen.DashboardScreen
import care.visify.feature.debug.api.DevTools
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BusinessViewModel @Inject constructor(
    private val router: VisifyRouter,
    private val currentUserHolder: CurrentUserHolder,
    private val intentHandler: IntentHandler,
    private val devTools: DevTools,
) : VisifyViewModel<BusinessState, BusinessEffect, BusinessIntent>() {

    init {
        handleIntent(BusinessIntent.LoadData)
    }

    override fun initialState(): BusinessState = BusinessState

    override fun handleIntent(intent: BusinessIntent) {
        when (intent) {
            BusinessIntent.LoadData -> loadInitialData()
            is BusinessIntent.HandleAndroidOnNewIntent -> handleAndroidOnNewIntent(intent.intent)
            is BusinessIntent.InitDevTools -> initDevTools(intent.activity)
        }
    }

    private fun handleAndroidOnNewIntent(intent: Intent?) {
        intentHandler.handle(intent)
    }

    private fun initDevTools(activity: Activity) {
        devTools.init(activity)
    }

    private fun loadInitialData() {
        viewModelScope.launchSafe {
            val isAuth = currentUserHolder.hasUser()
            when {
                isAuth.not() -> router.navigateTo(EmailOtpScreen(isAuthFailure = false))
                else -> router.newRootScreen(DashboardScreen)
            }
        }
    }
}
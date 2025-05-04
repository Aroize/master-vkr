package care.visify.client.feature.auth

import android.content.Context
import care.visify.client.R
import care.visify.feature.auth.api.GoogleSignInTokenProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ClientGoogleSignInTokenProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : GoogleSignInTokenProvider {
    override fun provideSignInToken(): String {
        return context.getString(R.string.default_web_client_id)
    }
}
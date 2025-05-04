package care.visify.business.core.auth

import android.content.Context
import care.visify.business.R
import care.visify.feature.auth.api.GoogleSignInTokenProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BusinessGoogleSignInTokenProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : GoogleSignInTokenProvider {
    override fun provideSignInToken(): String {
        //todo BUSINESS VALUE
        return context.getString(R.string.business_sign_in_token)
    }
}
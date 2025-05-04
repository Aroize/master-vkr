package care.visify.core.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings

object NotificationUtils {
    fun createNotificationSettingsIntent(ctx: Context): Intent {
        return Intent().apply {
            action = when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                -> Settings.ACTION_APP_NOTIFICATION_SETTINGS
                else
                -> Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            }
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                    putExtra(Settings.EXTRA_APP_PACKAGE, ctx.packageName)
                }
                else -> {
                    data = Uri.fromParts("package", ctx.packageName, null)
                }
            }
        }
    }
}
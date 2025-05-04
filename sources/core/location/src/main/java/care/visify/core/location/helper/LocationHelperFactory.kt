package care.visify.core.location.helper

import android.content.Context
import androidx.activity.result.ActivityResultCaller

object LocationHelperFactory {
    fun create(context: Context, caller: ActivityResultCaller): LocationHelper =
        LocationHelperImpl(context, caller)
}
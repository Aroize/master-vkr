package care.visify.core.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

object AppLifecycleHandler : LifecycleEventObserver {
    var isInBackground = true
        private set

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> isInBackground = false
            Lifecycle.Event.ON_STOP -> isInBackground = true
            else -> Unit
        }
    }
}
package care.visify.core.util

import android.os.Build


inline fun<T> atLeastApi(sdkVersion: Int, action: () -> T, fallback: () -> T): T {
    return if (Build.VERSION.SDK_INT >= sdkVersion) {
        action()
    } else {
        fallback()
    }
}

inline fun<T> atLeastR(action: () -> T, fallback: () -> T) {
    atLeastApi(Build.VERSION_CODES.R, action, fallback)
}


inline fun <T> atLeastTiramisu(action: () -> T, fallback: () -> T): T {
    return atLeastApi(Build.VERSION_CODES.TIRAMISU, action, fallback)
}
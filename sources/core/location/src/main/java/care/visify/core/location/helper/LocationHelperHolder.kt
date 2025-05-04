package care.visify.core.location.helper

import java.lang.ref.WeakReference

object LocationHelperHolder {

    private var reference = WeakReference<LocationHelper>(null)

    fun obtain(): LocationHelper =
        requireNotNull(reference.get()) { "LocationHelper reference isn't set" }

    fun setup(locationHelper: LocationHelper) {
        reference = WeakReference(locationHelper)
    }

    fun clear() {
        reference.clear()
    }
}
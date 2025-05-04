package care.visify.ui.kit.overlay.navigator

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import care.visify.core.navigator.api.compose.VisifyComposableScreen
import care.visify.core.util.atLeastTiramisu
import care.visify.ui.kit.overlay.container.OverlayContainerState

class OverlayNavigator<T : VisifyComposableScreen>(
    mutableTarget: MutableState<T>,
    val container: OverlayContainerState
): Parcelable {
    var target: T by mutableTarget
        private set

    constructor(parcel: Parcel) : this(
        mutableStateOf(
            restoreScreenFromParcel(parcel)
        ),
        restoreScreenFromParcel(parcel)
    )

    fun show() {
        container.show()
    }

    fun show(screen: T) {
        target = screen
        container.show()
    }

    fun hide(recovery : Boolean = true) {
        container.hide(recovery)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(target::class.java.name)
        parcel.writeParcelable(target, flags)
        parcel.writeString(container::class.java.name)
        parcel.writeParcelable(container as Parcelable, flags)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<OverlayNavigator<*>> {
        override fun createFromParcel(parcel: Parcel): OverlayNavigator<*> {
            return OverlayNavigator<VisifyComposableScreen>(parcel)
        }

        override fun newArray(size: Int): Array<OverlayNavigator<*>?> {
            return arrayOfNulls(size)
        }

        @SuppressLint("NewApi")
        private fun<T> restoreScreenFromParcel(parcel: Parcel): T {
            val className = requireNotNull(parcel.readString())
            val clazz = Class.forName(className)
            return atLeastTiramisu(
                action = {
                    parcel.readParcelable(
                        this::class.java.classLoader,
                        clazz
                    )
                },
                fallback = {
                    parcel.readParcelable(clazz.classLoader)
                }
            ) as T
        }
    }
}
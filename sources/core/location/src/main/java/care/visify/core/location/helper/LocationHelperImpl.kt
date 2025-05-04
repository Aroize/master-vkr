package care.visify.core.location.helper

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import care.visify.core.location.model.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LocationHelperImpl(
    private val context: Context,
    caller: ActivityResultCaller,
) : LocationHelper {

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    private var permissionGranted = CompletableDeferred<Boolean>()

    private val launcher = caller.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissionGranted.complete(
            permissions.entries.any { it.value }
        )
    }

    override fun hasPermissions(): Boolean {
        val fineLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarseLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        return fineLocationPermission || coarseLocationPermission
    }

    override suspend fun ensurePermissions() {
        if (hasPermissions().not()) {
            launcher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        } else {
            permissionGranted.complete(true)
        }
    }

    @SuppressLint("MissingPermission")
    override suspend fun requireLocation(): Location? {
        ensurePermissions()

        return if (permissionGranted.await()) {
            suspendCancellableCoroutine { continuation ->
                runCatching {
                    fusedLocationClient
                        .lastLocation
                        .addOnSuccessListener { location: android.location.Location? ->
                            location?.let {
                                continuation.resume(location.toLocation())
                            } ?: run {
                                continuation.resume(null)
                            }
                        }
                        .addOnFailureListener {
                            continuation.resume(null)
                        }
                }.onFailure {
                    continuation.resume(null)
                }
            }
        } else {
            null
        }
    }

    private fun android.location.Location.toLocation() = Location(
        latitude.toFloat(),
        longitude.toFloat()
    )

}
